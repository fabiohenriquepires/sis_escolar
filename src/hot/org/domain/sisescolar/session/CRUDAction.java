package org.domain.sisescolar.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.print.attribute.standard.Severity;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.edu.devmedia.sis_escolar.entidade.Pessoa;

@Name("crud")
@Scope(ScopeType.SESSION)
public class CRUDAction {
	
	@In FacesMessages facesMessages;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();	
	
	private Integer cont = 1;
	
	private Pessoa pessoa;
	
	private Pessoa pessoaAux;
	
	private Integer idPessoaSelecionada;
	
	public void salvarPessoa() {
		this.pessoa.setId(cont++);
		this.pessoas.add(pessoa);
		this.pessoa = new Pessoa();
		facesMessages.add("Pessoa cadastrada com sucesso!");
	}
	
	public void configIdAux(ActionEvent actionEvent) {
		this.idPessoaSelecionada = (Integer) actionEvent.getComponent().getAttributes().get("idPessoa");
	}
	
	public void popularPessoa(Integer idPessoa) {
		for(Pessoa p : this.pessoas) {
			if(idPessoa == p.getId()) {
				this.pessoaAux = p;
			}
		}
	}
	
	public void remover() {
		if(this.idPessoaSelecionada != 0) {			
			for (Iterator<Pessoa> iterator = pessoas.iterator(); iterator.hasNext();) {
				Pessoa pessoa = (Pessoa) iterator.next();
				if(pessoa.getId() == this.idPessoaSelecionada) {
					iterator.remove();
				}
			}
			facesMessages.add("Pessoa removida com sucesso!");
		} else {
			facesMessages.add("Erro ao deletar. Favor tente novamente!");
		}
	}
	
	public void atualizar() {
		Integer pos = -1 ;
		for(Pessoa p : this.pessoas) {
			if(p.getId() == this.pessoaAux.getId()) {
				pos = this.pessoas.indexOf(p);
			}
		}
		
		if (pos != -1) {
			this.pessoas.set(pos, pessoaAux);
			facesMessages.add("Pessoa atualizada com sucesso!");
		} else {
			facesMessages.add("Erro ao deletar. Favor tente novamente!");
		}
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public Pessoa getPessoa() {
		if(pessoa == null){
			pessoa = new Pessoa();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getIdPessoaSelecionada() {
		return idPessoaSelecionada;
	}

	public void setIdPessoaSelecionada(Integer idPessoaSelecionada) {
		this.idPessoaSelecionada = idPessoaSelecionada;
	}

	public Pessoa getPessoaAux() {
		return pessoaAux;
	}

	public void setPessoaAux(Pessoa pessoaAux) {
		this.pessoaAux = pessoaAux;
	}


}
