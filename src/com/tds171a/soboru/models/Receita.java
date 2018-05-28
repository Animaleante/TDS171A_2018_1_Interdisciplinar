package com.tds171a.soboru.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tds171a.soboru.utils.Utils;

/**
 * Model responsável pelas receitas
 * @author Luiz
 *
 */

@Entity
@Table(name = "RECEITAS")
public class Receita implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2787060387280213847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_sequence_generator_receita")
	@SequenceGenerator(name = "entity_sequence_generator_receita", sequenceName = "receita_seq")
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

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
	
	// FOREIGN KEYS --------------------------------------------------------------

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	// REFERENCED IN FOREIGN KEYS --------------------------------------------------------------
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="receita")
	private Set<ReceitaIngrediente> receitaIngredientes;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="receita")
	private Set<Comentario> comentarios;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="receita")
	private Set<Pontuacao> pontuacoes;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy = "receitasReportadas")
	private Set<Usuario> usuariosQueReportaram;

	@ManyToMany(fetch=FetchType.LAZY, mappedBy = "receitasFavoritadas")
	private Set<Usuario> usuariosQueFavoritaram;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="receitas_utensilios", joinColumns={ @JoinColumn(name="id_receita") }, inverseJoinColumns={ @JoinColumn(name="id_utensilio") })
	private Set<Utensilio> utensilios;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
		
		this.setSlug(Utils.toSlug(this.nome));
	}

	/**
	 * @return the porcao
	 */
	public Integer getPorcao() {
		return porcao;
	}

	/**
	 * @param porcao the porcao to set
	 */
	public void setPorcao(Integer porcao) {
		this.porcao = porcao;
	}

	/**
	 * @return the tempoPreparo
	 */
	public Double getTempoPreparo() {
		return tempoPreparo;
	}

	/**
	 * @param tempoPreparo the tempoPreparo to set
	 */
	public void setTempoPreparo(Double tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	/**
	 * @return the modoPreparo
	 */
	public String getModoPreparo() {
		return modoPreparo;
	}

	/**
	 * @param modoPreparo the modoPreparo to set
	 */
	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the pontuacaoMedia
	 */
	public double getPontuacaoMedia() {
		return pontuacaoMedia;
	}

	/**
	 * @param pontuacaoMedia the pontuacaoMedia to set
	 */
	public void setPontuacaoMedia(double pontuacaoMedia) {
		this.pontuacaoMedia = pontuacaoMedia;
	}

	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/**
	 * @return the favs
	 */
	public int getFavs() {
		return favs;
	}

	/**
	 * @param favs the favs to set
	 */
	public void setFavs(int favs) {
		this.favs = favs;
	}

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the aprovado
	 */
	public boolean isAprovado() {
		return aprovado;
	}

	/**
	 * @param aprovado the aprovado to set
	 */
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the receitaIngredientes
	 */
	public Set<ReceitaIngrediente> getReceitaIngredientes() {
		return receitaIngredientes;
	}

	/**
	 * @param receitaIngredientes the receitaIngredientes to set
	 */
	public void setReceitaIngredientes(Set<ReceitaIngrediente> receitaIngredientes) {
		this.receitaIngredientes = receitaIngredientes;
	}

	/**
	 * @return the comentarios
	 */
	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the pontuacoes
	 */
	public Set<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	/**
	 * @param pontuacoes the pontuacoes to set
	 */
	public void setPontuacoes(Set<Pontuacao> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}

	/**
	 * @return the usuariosQueReportaram
	 */
	public Set<Usuario> getUsuariosQueReportaram() {
		return usuariosQueReportaram;
	}

	/**
	 * @param usuariosQueReportaram the usuariosQueReportaram to set
	 */
	public void setUsuariosQueReportaram(Set<Usuario> usuariosQueReportaram) {
		this.usuariosQueReportaram = usuariosQueReportaram;
	}

	/**
	 * @return the usuariosQueFavoritaram
	 */
	public Set<Usuario> getUsuariosQueFavoritaram() {
		return usuariosQueFavoritaram;
	}

	/**
	 * @param usuariosQueFavoritaram the usuariosQueFavoritaram to set
	 */
	public void setUsuariosQueFavoritaram(Set<Usuario> usuariosQueFavoritaram) {
		this.usuariosQueFavoritaram = usuariosQueFavoritaram;
	}

	/**
	 * @return the utensilios
	 */
	public Set<Utensilio> getUtensilios() {
		return utensilios;
	}

	/**
	 * @param utensilios the utensilios to set
	 */
	public void setUtensilios(Set<Utensilio> utensilios) {
		this.utensilios = utensilios;
	}
}
