package com.tds171a.soboru.persistence;

import java.util.List;

import com.tds171a.soboru.models.Categoria;

public interface IDAO<T> {
	public boolean incluir(T model);
	public List<T> listar();
	public boolean atualizar(T model);
	boolean remover(Categoria model);
	public T selecionar(int modelId);
}
