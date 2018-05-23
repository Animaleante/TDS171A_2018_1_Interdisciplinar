package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Model responsável pelas receitas
 * @author Luiz
 *
 */

/**
 * Trandormando a model em entidade.
 * @author Luiz
 *
 */
@Entity
@Table(name = "RECEITAS")
public class Receita implements Serializable, Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_receita")
	@SequenceGenerator(name = "entity_sequence_generator_receita", sequenceName = "receita_seq")
	@Column(name = "id", nullable = false)
	private int id;

	/**
	 * Parametro nome da receita
	 */
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	@Column(name = "PORCAO", precision= 11, nullable = false)
	private Integer porcao;

	@Column(name = "TEMPO_PREPARO", precision= 11 , scale=2, nullable = false)
	private Double tempoPreparo;

	@Lob
	@Column(name = "MODO_PREPARO",  nullable = false)
	private String modoPreparo;

	@Column(name = "IMG_PATH", length=80, nullable = true)
	private String imgPath;

	@Column(name = "PONTUACAO_MEDIA", precision=11 , scale=2, nullable = false)
	private double pontuacaoMedia;

	@Column(name = "VIEWS", precision= 11, nullable = false)
	private int views;

	@Column(name = "FAVS", precision= 11, nullable = false)
	private int favs;

	@Column(name = "SLUG", length=80, nullable = false)
	private String slug;

	@Column(name = "APROVADO", precision= 1, nullable = false)
	private boolean aprovado;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "receitasReportadas")
	private Set<Utensilio> utensilios;
	
	private List<ReceitaIngrediente> receitaIngredientes;
	
	private List<Comentario> comentarios;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "receitas")
	private Set<Usuario> usuariosQueReportaram;

	@OneToMany(mappedBy="receita")
	private Set<Pontuacao> pontuacoes;
	
	private List<Usuario> usuariosFavoritaram;
	
	private int numComentarios;
}
