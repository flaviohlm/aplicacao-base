package br.gov.to.secad.main.service;

import br.gov.to.secad.main.repository.IMenuRepository;
import br.gov.to.secad.main.domain.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wandyer.silva
 */
@SuppressWarnings("unchecked")
@Service
public class MenuService implements Serializable {

    private final IMenuRepository repository;

    private static Logger logger = LogManager.getLogger();

    @Autowired
    public MenuService(IMenuRepository repository) {
        this.repository = repository;
    }

    public void save(Menu menu) {
        try {
            repository.save(menu);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void save(List<Menu> menu) {
        try {
            repository.save(menu);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void delete(Menu menu) {
        try {
            repository.delete(menu);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public List<Menu> findAll() {
        try {
            return repository.findAllMenu();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public List<Menu> findByAtivo() {
        try {
            return repository.findByAtivo();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Menu findOne(Integer id) {
        try {
            return repository.findOne(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    public List<Menu> findByPerfil(Integer idPerfil) {
        try {
            return repository.findByPerfil(idPerfil);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public List<Menu> findByNivel(Integer idNivel) {
        try {
            return repository.findByNivel(idNivel);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public List<Menu> findByNivelDesativados(Integer idNivel) {
        try {
            return repository.findByNivelDesativados(idNivel);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public List<Menu> findByPai(Integer idPai) {
        try {
            return repository.findByPai(idPai);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public List<Menu> findByPaiOrdem(Integer idPai, Integer ordem) {
        try {
            return repository.findByPaiOrdem(idPai, ordem);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public TreeNode createCheckBoxTreeNode(List<Menu> listaMenuPerfil) {

        List<Menu> menusPai = new ArrayList<>(); //Menus nível 1
        List<Menu> menusFilhos = new ArrayList<>(); //Menus nível 2 ou 3

        if (listaMenuPerfil != null) {
            for (Menu m : listaMenuPerfil) {
                if (m.getNivel() == 1) {
                    menusPai.add(m);
                } else {
                    menusFilhos.add(m);
                }
            }
        }


        TreeNode root = new DefaultTreeNode(null, null);
        root.setExpanded(true);

        TreeNode paiTreeNode;
        for (Menu pai : menusPai) {
            paiTreeNode = new DefaultTreeNode(pai, root);
            paiTreeNode.setExpanded(true);

            //Consultar filhos desse menu
            List<Menu> filhos = menusFilhos.stream()
                    .filter(str -> pai.getId().equals(str.getPai().getId()))
                    .collect(Collectors.toList());

            // Filhos
            TreeNode filhosTreeNode;
            for (Menu filho : filhos) {

                filhosTreeNode = new DefaultTreeNode(filho, paiTreeNode);
                filhosTreeNode.setExpanded(true);

                //Verificar se esse filho possuí filhos
                List<Menu> filhoFilhos = menusFilhos.stream()
                        .filter(str -> filho.getId().equals(str.getPai().getId()))
                        .collect(Collectors.toList());

                // Netos
                TreeNode netosTreeNode;
                for (Menu filhoFilho : filhoFilhos) {
                    netosTreeNode = new DefaultTreeNode(filhoFilho, filhosTreeNode);
                    netosTreeNode.setExpanded(true);

                }
            }
        }
        if (root.getChildCount() == 0) {
            return null;
        }
        return root;
    }
}
