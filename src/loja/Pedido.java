package loja;

import java.util.Date;
import java.util.List;

public class Pedido {
    private int numero;
    private Date dataPedido;
    private Date dataEntrega;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private String status;

    public Pedido(int numero, Date dataPedido, Cliente cliente, List<ItemPedido> itens, String status) {
        this.numero = numero;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.itens = itens;
        this.status = status;
    }

    public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", dataPedido=" + dataPedido +
                ", dataEntrega=" + dataEntrega +
                ", status='" + status + '\'' +
                ", cliente=" + cliente +
                ", itens=" + itens +
                '}';
    }

	
}
