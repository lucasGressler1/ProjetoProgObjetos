package loja;

public class Estoque {
    private int quantidade;
    private int preco;

    public Estoque(int quantidade, int preco) {
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}
	
	public void adicionaQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }
	
	public void removeQuantidade(int quantidade) {
	    if (this.quantidade >= quantidade) {
	        this.quantidade -= quantidade;
	    } else {
	        System.out.println("Erro ao remover quantidade do estoque.");
	    }
	}

    @Override
    public String toString() {
        return "Estoque{" +
                "quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }

	
}
