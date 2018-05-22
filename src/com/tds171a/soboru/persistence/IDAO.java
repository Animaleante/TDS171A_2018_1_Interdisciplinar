package com.tds171a.soboru.persistence;

import java.util.List;

public interface IDAO<T> {
	public boolean incluir(T model);
	public List<T> listar();
	public boolean atualizar(T model);
	boolean remover(T model);
	public T selecionar(int modelId);
}
