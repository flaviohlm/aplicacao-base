package br.gov.to.secad.main.config.security;

import br.gov.to.secad.main.domain.Menu;
import br.gov.to.secad.main.domain.Perfil;
import br.gov.to.secad.main.security.filter.JsfRedirectStrategy;
import br.gov.to.secad.main.security.handler.FailureHandler;
import br.gov.to.secad.main.security.handler.SuccessHandler;
import br.gov.to.secad.main.security.provider.DefaultAuthenticationProvider;
import br.gov.to.secad.main.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import java.util.List;

/**
 * @author wandyer.silva
 */
@SuppressWarnings("Duplicates")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DefaultAuthenticationProvider authenticationProvider;

    private final MenuService menuService;

    private static Logger logger = LogManager.getLogger();

    @Autowired
    public SecurityConfig(DefaultAuthenticationProvider authenticationProvider, MenuService menuService) {
        this.authenticationProvider = authenticationProvider;
        this.menuService = menuService;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/javax.faces.resource/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        configureAntMatchers(http);

        http
            .authorizeRequests()
            .antMatchers("/erro/**").permitAll()
            .antMatchers("/login/**").permitAll()
            .anyRequest().authenticated()

            .and()

            .csrf().disable()

            .formLogin()
                .loginPage("/login/index.xhtml")
                .permitAll()
                .usernameParameter("login")
                .passwordParameter("senha")
                .successHandler(new SuccessHandler())
                .failureHandler(new FailureHandler())

            .and()

            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login/index.xhtml")

            .and()

            .exceptionHandling()
                .accessDeniedPage("/erro/permissao/index.xhtml")

            .and()

            .sessionManagement()
                .invalidSessionUrl("/erro/sessao/index.xhtml")
                .sessionAuthenticationErrorUrl("/login/index.xhtml")

            .and()

            .addFilterBefore(expiredSessionFilter(), SessionManagementFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
            .authenticationProvider(authenticationProvider)
            .eraseCredentials(false);
    }

    /**
     * Método para configurar as URLs e suas permissões dinamicamente através do que foi cadastrado
     * no banco de dados.
     * @param http .
     * @throws Exception .
     */
    private void configureAntMatchers(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()

            // Java Melody
            .antMatchers("/monitor").permitAll()

            // Páginas públicas Unicet
            .antMatchers("/unicet/*").permitAll()
            .antMatchers("/unicet/instrutor/*").permitAll()
            .antMatchers("/unicet/instrutor/inscricoes-encerradas/*").permitAll()
            .antMatchers("/unicet/instrutor/visualizar-curso/*").permitAll();

        //Buscar menus do nível 1
        List<Menu> menuList = menuService.findByNivel(1);

        menuList
                .forEach(menu -> {

                    // Nenhum perfil para esse menu
                    if (menu.getPerfis().isEmpty()) {
                        return;
                    }

                    // Remover index.xhtml do final da URL
                    if (menu.getUrl().contains("index.xhtml")) {
                        menu.setUrl(menu.getUrl().replace("index.html", ""));
                    }

                    // Acrescentar '/' no final da URL
                    if (!menu.getUrl().endsWith("/")) {
                        menu.setUrl(menu.getUrl() + "/");
                    }

                    // Todos os nomes dos perfis desse menu
                    String[] authorities = menu.getPerfis().stream()
                            .map(Perfil::getNome)
                            .toArray(String[]::new);
                    try {
                        http.authorizeRequests()
                                .antMatchers(menu.getUrl() + "**")
                                .hasAnyAuthority(authorities);
                    } catch (Exception e) {
                        logger.error(e);
                    }
                });


        //Buscar menus do nível 1 desativados
        List<Menu> menuDesativadosList = menuService.findByNivelDesativados(1);

        menuDesativadosList
                .forEach(menu -> {

                    // Remover index.xhtml do final da URL
                    if (menu.getUrl().contains("index.xhtml")) {
                        menu.setUrl(menu.getUrl().replace("index.html", ""));
                    }

                    // Acrescentar '/' no final da URL
                    if (!menu.getUrl().endsWith("/")) {
                        menu.setUrl(menu.getUrl() + "/");
                    }

                    try {
                        http.authorizeRequests()
                                .antMatchers(menu.getUrl() + "**").denyAll();
                    } catch (Exception e) {
                        logger.error(e);
                    }
                });
    }

    @Bean
    public Filter expiredSessionFilter() {
        SessionManagementFilter smf = new SessionManagementFilter(new HttpSessionSecurityContextRepository());
        smf.setInvalidSessionStrategy(new JsfRedirectStrategy());
        return smf;
    }
}
