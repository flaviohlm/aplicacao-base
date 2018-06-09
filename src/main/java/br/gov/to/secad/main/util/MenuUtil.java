package br.gov.to.secad.main.util;

import br.gov.to.secad.main.domain.Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MenuUtil {

    /**
     * Filtrar uma lista de menus de um determinado nível, tipo e o ID de um pai
     *
     * @param listaMenus a lista de menus para ser filtrado
     * @param nivel o nivel dos menus para ser filtrado
     * @param idMenuPai o ID do pai para filtrar na lista de menus
     * @param tipoMenu o tipo de menu para ser filtrado
     * @return a lista de menus filtrada
     */
    public List<Menu> menuModulo(List<Menu> listaMenus, Integer nivel, Integer idMenuPai, Integer tipoMenu) {
        return listaMenus.stream()
                .filter(x -> Objects.equals(x.getNivel(), nivel))
                .filter(x -> idMenuPai == null || Objects.equals(x.getPai().getId(), idMenuPai))
                .collect(Collectors.toList());
    }

    /**
     * Busca os filhos de um determinado menu
     *
     * @param menu      .
     * @param listaMenu .
     * @return .
     */
    public List<Menu> buscarFilhosMenu(Menu menu, List<Menu> listaMenu) {

        return listaMenu.stream()
                .filter(x -> x.getPai() != null)
                .filter(x -> x.getPai().equals(menu))
                .collect(Collectors.toList());
    }
}

