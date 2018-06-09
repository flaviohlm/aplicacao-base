$(document).ready(function () {
    let $this = PF('me');
    if ($this) {

        let pathname = window.location.pathname;
        let ocurrencesofSlash = pathname.split("/").length - 1;

        if (pathname.indexOf('index.xhtml') === -1) {
            pathname = pathname + "index.xhtml"
        }

        if (ocurrencesofSlash > 2) {
            if (pathname.indexOf('.xhtml') > 0) {
                let activeLink = $this.menulinks.filter('[href^="' + pathname + '"]');

                //Caso não encontre nenhum menu para ativar, é porque está dentro de uma página que não possui menu
                //Então, vamos tentar descobrir quem é o primeiro pai dessa página para ativar
                if (ocurrencesofSlash > 3) {
                    if (activeLink.length === 0) {
                        pathname = pathname.substring(0, pathname.indexOf("index"));

                        let slashAux = pathname.split("/").length - 1;
                        slashAux = slashAux - 3;

                        for (let i = 0; i < slashAux; i++) {
                            pathname = pathname.substr(0, pathname.lastIndexOf('/'));
                        }
                        pathname = pathname + "/index.xhtml";

                        activeLink = $this.menulinks.filter('[href^="' + pathname + '"]');

                        // Entra aqui caso não esteja dentro de um sistema
                        if (activeLink.length === 0) {
                            pathname = pathname.substring(0, pathname.indexOf("index"));

                            let slashAux = pathname.split("/").length - 1;
                            slashAux = slashAux - 2; // Essa é a diferençã da condição anterior

                            for (let i = 0; i < slashAux; i++) {
                                pathname = pathname.substr(0, pathname.lastIndexOf('/'));
                            }

                            pathname = pathname + "/index.xhtml";

                            activeLink = $this.menulinks.filter('[href^="' + pathname + '"]');
                        }
                    }
                }

                if (activeLink.length === 1) {
                    $this.expandedMenuitems = [];
                    $this.clearMenuState();

                    let item = activeLink.parent('li');
                    $this.addMenuitem(item.attr('id'));
                    addParentItems($this, item);
                    rc();
                }

                // Caso for o index de um sistema, expandir a lista de filhos do sistema
                if (ocurrencesofSlash === 3) {
                    let pathname2 = window.location.pathname;
                    pathname2 = pathname2 + "#";

                    let result = $this.menulinks.filter(function (index) {
                        let atual = $this.menulinks[index];
                        return atual.href.includes(pathname2) && atual.childElementCount > 0;
                    });

                    if (result.length === 1) {
                        $this.expandedMenuitems = [];
                        $this.clearMenuState();
                        let item2 = result.parent('li');
                        $this.addMenuitem(item2.attr('id'));
                        rc();
                    }

                }
            }
        }
        else {
            $this.expandedMenuitems = [];
            $this.clearMenuState();
            $this.clearActiveItems();
            // rc();
        }
    }
});

function addParentItems($this, item) {
    if (item && item.length) {
        let parent = item.parents('li:first');
        $this.addMenuitem(parent.attr('id'));
        if (!parent.parent('ul').hasClass('ultima-menu')) {
            addParentItems($this, parent);
        }
    }
}