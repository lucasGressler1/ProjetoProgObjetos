package loja;

public class Produto {
	
	private int id;
    private String nome;
    private String descricao;
    private Fornecedor fornecedor;
    private Estoque estoque;
    private double preco;

    public Produto(int id, String nome, String descricao, Fornecedor fornecedor, Estoque estoque, double preco, int quantidadeEstoque, int precoEstoque ) {
    	this.id = id;
    	this.nome = nome;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.estoque = new Estoque(quantidadeEstoque, precoEstoque);
        this.preco = preco;
    }

    public String getNome() {
		return nome;
	}
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Estoque getEstoque() {
		return estoque;
	}
	
	public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public void adicionaEstoque(int quantidade) {
        this.estoque.adicionaQuantidade(quantidade); 
    }
	
	public void removeEstoque(int quantidade) {
	    this.estoque.removeQuantidade(quantidade);
	}

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fornecedor=" + fornecedor +
                ", estoque=" + estoque +
                '}';
    }
}
