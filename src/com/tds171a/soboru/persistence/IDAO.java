package com.tds171a.soboru.persistence;

import java.util.List;

public interface IDAO<T> {
	public boolean incluir(T model);
	public List<T> listar();
	public boolean atualizar(T model);
	public boolean remover(int modelId);
	public T selecionar(int modelId);
}
