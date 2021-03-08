package br.com.devmedia.introducaojpql.consultas;

import br.com.devmedia.introducaojpql.dao.JPAUtil;
import br.com.devmedia.introducaojpql.domain.Episodio;
import br.com.devmedia.introducaojpql.domain.Serie;

import javax.persistence.EntityManager;
import java.util.List;

public class ConsultasJPQL {

    //Consulta para obter todas as séries
    public List<Serie> listarTodasAsSeries() {
        EntityManager em = JPAUtil.getEntityManager();

        String queryJPQL = "SELECT s FROM Serie s";

        return em.createQuery(queryJPQL, Serie.class)
                .getResultList();
    }

    //Consulta por séries criadas no ano 2008
    public List<Serie> listarSeriesCriadasNoAno2008() {
        EntityManager em = JPAUtil.getEntityManager();

        String queryJPQL = "SELECT s FROM Serie s WHERE s.anoCriacao = 2008";

        return em.createQuery(queryJPQL, Serie.class)
                .getResultList();
    }

    //Consulta por séries criadas no ano especificado pelo usuário
    public List<Serie> listarSeriesCriadasNoAno(int ano) {
        EntityManager em = JPAUtil.getEntityManager();

        String queryJPQL = "SELECT s FROM Serie s WHERE s.anoCriacao = :anoDesejado";

        return em.createQuery(queryJPQL, Serie.class)
                .setParameter("anoDesejado", ano)
                .getResultList();
    }

    //Consulta por séries cujo nome contenha a substring informada pelo usuário
    public List<Serie> listarSeriesCujoNomeContenha(String nome) {
        EntityManager em = JPAUtil.getEntityManager();

        String queryJPQL = "SELECT s FROM Serie s WHERE s.nome LIKE :nome";

        return em.createQuery(queryJPQL, Serie.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    //Consulta que retorna apenas o nome da série utilizando filtro com dois critérios
    public String recuperarSeriePorAnoENome(String nome, int ano) {
        EntityManager em = JPAUtil.getEntityManager();
        String queryJpql = "SELECT s.nome FROM Serie s WHERE s.nome LIKE :nome AND s.anoCriacao = :ano";

        return em.createQuery(queryJpql, String.class)
                .setParameter("nome", nome)
                .setParameter("ano", ano)
                .getSingleResult();
    }

    //Consulta que envolve mais de uma entidade sem declarar o JOIN
    public List<Episodio> listarEpisodiosDaSerie(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        String queryJpql = "SELECT e FROM Episodio e WHERE e.temporada.serie.nome LIKE :nome";

        return em.createQuery(queryJpql, Episodio.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    //Consulta que envolve mais de uma entidade e o uso do Join
    public List<Serie> recuperarSeriesComNumeroMinimoDeTemporadas(int temporadas) {
        EntityManager em = JPAUtil.getEntityManager();
        String queryJpql = "SELECT s FROM Serie s JOIN s.temporadas t WHERE t.numero = :qtdTemporadas";

        return em.createQuery(queryJpql, Serie.class)
                .setParameter("qtdTemporadas", temporadas)
                .getResultList();
    }

}
