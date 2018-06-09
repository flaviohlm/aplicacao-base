package br.gov.to.secad.ultima.menu;

import br.gov.to.secad.main.domain.Menu;
import br.gov.to.secad.main.view.AuthenticationController;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wandyer.silva
 */
@ManagedBean
@SessionScoped
public class MenuView {

	@ManagedProperty(value = "#{authenticationController}")
	private AuthenticationController authenticationController;

	private MenuModel model;

	private List<Menu> menusAcesso;

	private List<Menu> menusPais;

	private List<Menu> menusFilhos;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		menusAcesso = authenticationController.getListaMenusAcesso();

		if (menusAcesso == null) {
			return;
		}

		menusPais = new ArrayList<>();
		menusFilhos = new ArrayList<>();

		for (Menu m : menusAcesso) {
			//Submenus
			if (m.getNivel() == 1) {
				// Menus nível 1
				menusPais.add(m);
			} else {
				// Menus de nível 2 ou mais
				menusFilhos.add(m);
			}

		}


		createMenuModel();
	}

	public void createMenuModel() {
		model = new DefaultMenuModel();

		// Menus de serviços do Portal
		for (Menu serv : menusPais) {
			//Consultar menu filhos

			List<Menu> filhos = menusFilhos.stream()
					.filter(str -> serv.getId().equals(str.getPai().getId()))
					.collect(Collectors.toList());

			//Menu não possui filhos
			if (filhos.isEmpty()) {
				//O menu é um menuElement
				model.addElement(addMenuElement(serv));
			} else { //Menu possui filhos

				//O menu é um subMenu
				DefaultSubMenu subMenu = new DefaultSubMenu();
				subMenu.setLabel(serv.getDescricao());
				subMenu.setIcon(serv.getIcone());
				subMenu.setId(serv.getId().toString());

				for (Menu filho : filhos) {
					//Verificar se os filhos possuiem filhos

					List<Menu> filhoFilhos = menusFilhos.stream()
							.filter(str -> filho.getId().equals(str.getPai().getId()))
							.collect(Collectors.toList());

					//Filho não possui filhos
					if (filhoFilhos.isEmpty()) {
						//O filho é um menuElement
						subMenu.addElement(addMenuElement(filho));
					} else { //Filho

						//O filho é um subMenu
						DefaultSubMenu subMenuFilho = new DefaultSubMenu();
						subMenuFilho.setLabel(filho.getDescricao());
						subMenuFilho.setIcon(filho.getIcone());
						subMenuFilho.setId(filho.getId().toString());

						for (Menu filhoFilho : filhoFilhos) {
							subMenuFilho.addElement(addMenuElement(filhoFilho));
						}
						subMenu.addElement(subMenuFilho);
					}
				}

				model.addElement(subMenu);

			}
		}
	}

	private DefaultMenuItem addMenuElement(Menu menu) {

		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		String urlArray[] = viewId.split("/");

		DefaultMenuItem menuItem = new DefaultMenuItem(menu.getDescricao());
		menuItem.setIcon(menu.getIcone());

		menuItem.setOutcome(gerarURL(menu.getPai(), menu));

		menuItem.setId(menu.getId().toString());

		if (urlArray.length > 2) {
			String descricao = removerAcento(menu.getDescricao().toUpperCase());
			if (descricao.contains(urlArray[2].toUpperCase())) {
				menuItem.setContainerStyleClass("active-menuitem");
			}
		}
		return menuItem;
	}

	private String gerarURL(Menu pai, Menu filho) {
		if (pai == null) {
			return filho.getUrl();
		}

		//Remover faces-redirect=true
		pai.setUrl(pai.getUrl().replace("?faces-redirect=true", ""));
		filho.setUrl(filho.getUrl().replace("?faces-redirect=true", ""));

		// Remover indices do menu pai no menu filho
		if (!pai.getUrl().equalsIgnoreCase("")) { // apenas se a URL do pai não for vazia
			if (filho.getUrl().contains(pai.getUrl())) {
				if (!pai.getUrl().equalsIgnoreCase("/")) {
					filho.setUrl(filho.getUrl().replace(pai.getUrl(), ""));
				}
			}
		}

		// Menu terceiro nivel
		if (filho.getNivel() == 3) {
			String sUrlNivel2 = limparMenuPai(pai.getUrl(), pai.getNivel()) + filho.getUrl();

			return limparMenuPai(pai.getPai().getUrl(), pai.getPai().getNivel()) + sUrlNivel2;
		}

		return limparMenuPai(pai.getUrl(), pai.getNivel()) + filho.getUrl();
	}

	/**
	 * Método para limpar a url do menu pai
	 *
	 * @param urlPai a url a ser limpa
	 * @return a url do menu
	 */
	private String limparMenuPai(String urlPai, Integer nivel) {
		// Apenas se a URL do pai não for vazia
		if (!urlPai.equalsIgnoreCase("")) {
			// Remover index.xhtml do menu pai
			urlPai = urlPai.replace("index.xhtml", "");

			// Adicionar '/' do final do menu pai
			if (!urlPai.endsWith("/")) {
				urlPai = urlPai + "/";
			}

			if (nivel != 2) {
				// Adicionar '/' no inicio do menu pai
				if (!urlPai.startsWith("/")) {
					urlPai = "/" + urlPai;
				}
			}
		}

		return urlPai;
	}

	private String removerAcento(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	public static MenuView getCurrentInstance() {
		return (MenuView) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menuView");
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public List<Menu> getMenusAcesso() {
		return menusAcesso;
	}

	public void setMenusAcesso(List<Menu> menusAcesso) {
		this.menusAcesso = menusAcesso;
	}

	public List<Menu> getMenusFilhos() {
		return menusFilhos;
	}

	public void setMenusFilhos(List<Menu> menusFilhos) {
		this.menusFilhos = menusFilhos;
	}

	public List<Menu> getMenusPais() {
		return menusPais;
	}

	public void setMenusPais(List<Menu> menusPais) {
		this.menusPais = menusPais;
	}

	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	public void setAuthenticationController(AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}
}
