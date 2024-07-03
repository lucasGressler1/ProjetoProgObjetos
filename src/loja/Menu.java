package loja;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Menu {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private int countFornec = 0;
    private int countProd = 0;
    private int countPedidos = 0;

    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        
        menu.carregarDados();  // Carrega as lista gson p
        menu.menuPrincipal(scan);
        menu.salvarDados();  // Salva os dados ao finalizar o programa
    }
    
    private void salvarDados() {
        Gson gson = new Gson();
        
        try (FileWriter writer = new FileWriter("fornecedores.json")) {
            gson.toJson(fornecedores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("produtos.json")) {
            gson.toJson(produtos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("pedidos.json")) {
            gson.toJson(pedidos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("clientes.json")) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        Gson gson = new Gson();
        File fornecedoresFile = new File("fornecedores.json");
        File produtosFile = new File("produtos.json");
        File pedidosFile = new File("pedidos.json");
        File clientesFile = new File("clientes.json");

        if (fornecedoresFile.exists()) {
            try (FileReader reader = new FileReader(fornecedoresFile)) {
                Type listType = new TypeToken<ArrayList<Fornecedor>>() {}.getType();
                fornecedores = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (produtosFile.exists()) {
            try (FileReader reader = new FileReader(produtosFile)) {
                Type listType = new TypeToken<ArrayList<Produto>>() {}.getType();
                produtos = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (pedidosFile.exists()) {
            try (FileReader reader = new FileReader(pedidosFile)) {
                Type listType = new TypeToken<ArrayList<Pedido>>() {}.getType();
                pedidos = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (clientesFile.exists()) {
            try (FileReader reader = new FileReader(clientesFile)) {
                Type listType = new TypeToken<ArrayList<Cliente>>() {}.getType();
                clientes = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void menuPrincipal(Scanner scan) {
        int opcao = 0;

        do {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│    Menu Principal:                          │");
            System.out.println("│    1 - Menu Administrador                   │");
            System.out.println("│    2 - Menu Cliente                         │");
            System.out.println("│    0 - Sair e salvar (gson)                 │");
            System.out.println("└─────────────────────────────────────────────┘");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    menuAdmin(scan);
                    break;
                case 2:
                    menuCliente(scan); 
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void menuAdmin(Scanner scan) {
        int opcao = 0;

        do {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│    Menu Administrador:                      │");
            System.out.println("│    1 - menu Fornecedores            	      │");
            System.out.println("│    2 - menu Produtos                        │");
            System.out.println("│    3 - Manutenção de Estoque                │");
            System.out.println("│    0 - Voltar                               │");
            System.out.println("└─────────────────────────────────────────────┘");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    menuFornecedor(scan);
                    break;
                case 2:
                    menuProduto(scan);
                    break;
                case 3:
                    manutencaoEstoque(scan);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void menuFornecedor(Scanner scan) {
        int opcao = 0;

        do {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│    Menu de Fornecedores:                    │");
            System.out.println("│    1 - Cadastrar Fornecedor                 │");
            System.out.println("│    2 - Alterar Fornecedor                   │");
            System.out.println("│    3 - Excluir Fornecedor                   │");
            System.out.println("│    4 - Consultar Fornecedor                 │");
            System.out.println("│    0 - Voltar                               │");
            System.out.println("└─────────────────────────────────────────────┘");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastraFornecedor(scan);
                    break;
                case 2:
                    alteraFornecedor(scan);
                    break;
                case 3:
                    excluiFornecedor(scan);
                    break;
                case 4:
                    consultaFornecedor(scan);
                    break;
                case 0:
                    System.out.println("Voltando ao menu administrador...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void listaFornecedores() {
        System.out.println("Lista de Fornecedores:");
        for (Fornecedor fornecedor : fornecedores) {
            System.out.println("ID: " + fornecedor.getId() + " | Nome: " + fornecedor.getNome());
        }
    }
    
    private boolean validaEmail(String email) {
        return email.toLowerCase().endsWith("@gmail.com");
    }
    
    public void cadastraFornecedor(Scanner scan) {
        
        int id = 0;
        boolean codigoValido = false;
        while (!codigoValido) {
            System.out.print("Codigo fornecedor: ");
            try {
            	id = Integer.parseInt(scan.nextLine());
            	codigoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Descrição: ");
        String descricao = scan.nextLine();

        System.out.print("Telefone: ");
        long telefone = 0;
        boolean telefoneValido = false;
        while (!telefoneValido) {
            try {
                telefone = Long.parseLong(scan.nextLine());
                telefoneValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número de telefone válido.");
            }
        }

        String email;
        do {
            System.out.print("Email (deve terminar com '@gmail.com'): ");
            email = scan.nextLine();
        } while (!validaEmail(email));

        System.out.println("Endereço:");
        System.out.print("Rua: ");
        String rua = scan.nextLine();
     
        int numero = 0;
        boolean numeroValidoCadastro = false;
        while (!numeroValidoCadastro) {
            System.out.print("Numero do endereco: ");
            try {
            	numero = Integer.parseInt(scan.nextLine());
            	numeroValidoCadastro = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.print("Complemento: ");
        String complemento = scan.nextLine();
        System.out.print("Bairro: ");
        String bairro = scan.nextLine();
        
        int cep = 0;
        boolean cepValido = false;
        while (!cepValido) {
            System.out.print("CEP do endereco: ");
            try {
            	cep = Integer.parseInt(scan.nextLine());
            	cepValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.print("Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Estado: ");
        String estado = scan.nextLine();

        Endereco endereco = new Endereco(rua, numero, complemento, bairro, cep, cidade, estado);
        Fornecedor fornecedor = new Fornecedor(id, nome, descricao, telefone, email, endereco);

        fornecedores.add(fornecedor);
        countFornec++;
        System.out.println("Fornecedor adicionado com sucesso.");
    }

    public void alteraFornecedor(Scanner scan) {
    	listaFornecedores();
    	
        System.out.println("Alterar por:\n1. Código\n2. Nome");
        System.out.print("Escolha uma opção: ");
        int escolha = scan.nextInt();
        scan.nextLine();

        Fornecedor fornecedorParaAlterar = null;

        switch (escolha) {
            case 1:
                System.out.print("Código do Fornecedor a ser alterado: ");
                int codigo = scan.nextInt();
                scan.nextLine();
                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getId() == codigo) {
                        fornecedorParaAlterar = fornecedor;
                        break;
                    }
                }
                if (fornecedorParaAlterar == null) {
                    System.out.println("Fornecedor não encontrado.");
                    return;
                }
                break;
            case 2:
                System.out.print("Nome do Fornecedor a ser alterado: ");
                String nome = scan.nextLine();
                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getNome().equalsIgnoreCase(nome)) {
                        fornecedorParaAlterar = fornecedor;
                        break;
                    }
                }
                if (fornecedorParaAlterar == null) {
                    System.out.println("Fornecedor não encontrado.");
                    return;
                }
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        boolean alterando = true;
        while (alterando) {
            System.out.println("Selecione o campo a ser alterado:");
            System.out.println("1. Nome");
            System.out.println("2. Descrição");
            System.out.println("3. Telefone");
            System.out.println("4. Email");
            System.out.println("5. Endereço");
            System.out.println("6. Salvar e Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Novo Nome: ");
                    String novoNome = scan.nextLine();
                    fornecedorParaAlterar.setNome(novoNome);
                    break;
                case 2:
                    System.out.print("Nova Descrição: ");
                    String novaDescricao = scan.nextLine();
                    fornecedorParaAlterar.setDescricao(novaDescricao);
                    break;
                case 3:
                    System.out.print("Novo Telefone: ");
                    long novoTelefone = scan.nextInt();
                    scan.nextLine();
                    fornecedorParaAlterar.setTelefone(novoTelefone);
                    break;
                case 4:
                	String novoEmail;
                    do {
                        System.out.print("Novo Email (deve terminar com '@gmail.com'): ");
                        novoEmail = scan.nextLine();
                    } while (!validaEmail(novoEmail));
                    fornecedorParaAlterar.setEmail(novoEmail);
                    break;
                case 5:
                    System.out.println("Novo Endereço:");
                    System.out.print("Rua: ");
                    String novaRua = scan.nextLine();

                    int novoNumero = 0;
                    boolean numeroValido = false;
                    while (!numeroValido) {
                        System.out.print("Numero do endereco: ");
                        try {
                            novoNumero = Integer.parseInt(scan.nextLine());
                            numeroValido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, insira um número inteiro válido.");
                        }
                    }

                    System.out.print("Complemento: ");
                    String novoComplemento = scan.nextLine();
                    System.out.print("Bairro: ");
                    String novoBairro = scan.nextLine();

                    System.out.print("CEP: ");
                    int novoCep = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Cidade: ");
                    String novaCidade = scan.nextLine();
                    System.out.print("Estado: ");
                    String novoEstado = scan.nextLine();

                    Endereco novoEndereco = new Endereco(novaRua, novoNumero, novoComplemento, novoBairro, novoCep, novaCidade, novoEstado);
                    fornecedorParaAlterar.setEndereco(novoEndereco);
                    break;
                case 6:
                    alterando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        System.out.println("Fornecedor alterado com sucesso.");
    }

    public void excluiFornecedor(Scanner scan) {
    	listaFornecedores();
    	
        System.out.println("Excluir por:\n1. Código\n2. Nome");
        System.out.print("Escolha uma opção: ");
        int escolha = scan.nextInt();
        scan.nextLine();

        switch (escolha) {
            case 1:
                System.out.print("Código do Fornecedor a ser excluído: ");
                int codigo = scan.nextInt();
                scan.nextLine();
                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getId() == codigo) {
                        fornecedores.remove(fornecedor);
                        countFornec--;
                        System.out.println("Fornecedor excluído com sucesso.");
                        return;
                    }
                }
                System.out.println("Fornecedor não encontrado.");
                break;
            case 2:
                System.out.print("Nome do Fornecedor a ser excluído: ");
                String nome = scan.nextLine();
                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getNome().equalsIgnoreCase(nome)) {
                        fornecedores.remove(fornecedor);
                        countFornec--;
                        System.out.println("Fornecedor excluído com sucesso.");
                        return;
                    }
                }
                System.out.println("Fornecedor não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void consultaFornecedor(Scanner scan) {
    	listaFornecedores();
    	
        System.out.println("Consultar por:\n1. Código\n2. Nome");
        System.out.print("Escolha uma opção: ");
        int escolha = scan.nextInt();
        scan.nextLine();

        switch (escolha) {
            case 1:
                System.out.print("Código do Fornecedor a ser consultado: ");
                int codigo = scan.nextInt();
                scan.nextLine();
                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getId() == codigo) {
                        System.out.println(fornecedor);
                        return;
                    }
                }
                System.out.println("Fornecedor não encontrado.");
                break;
            case 2:
                System.out.print("Nome do Fornecedor a ser consultado: ");
                String nome = scan.nextLine();
                for (Fornecedor fornecedor : fornecedores) {
                    if (fornecedor.getNome().equalsIgnoreCase(nome)) {
                        System.out.println(fornecedor);
                        return;
                    }
                }
                System.out.println("Fornecedor não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuProduto(Scanner scan) {
        int opcao = 0;

        do {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│    Menu de Produtos:                        │");
            System.out.println("│    1 - Cadastrar Produto                    │");
            System.out.println("│    2 - Alterar Produto                      │");
            System.out.println("│    3 - Excluir Produto                      │");
            System.out.println("│    4 - Consultar Produto                    │");
            System.out.println("│    0 - Voltar                               │");
            System.out.println("└─────────────────────────────────────────────┘");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastraProduto(scan);
                    break;
                case 2:
                    alteraProduto(scan);
                    break;
                case 3:
                    excluiProduto(scan);
                    break;
                case 4:
                    consultaProduto(scan);
                    break;
                case 0:
                    System.out.println("Voltando ao menu administrador...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public void cadastraProduto(Scanner scan) {
        if (countProd > produtos.size()) {
            System.out.println("Capacidade máxima de produtos atingida.");
            return;
        }

        int id = 0;
        boolean idValido = false;
        while (!idValido) {
            System.out.print("ID/Código: ");
            try {
                id = Integer.parseInt(scan.nextLine());
                idValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("Descrição: ");
        String descricao = scan.nextLine();
        
        System.out.print("Preco (unidade): ");
        int preco = Integer.parseInt(scan.nextLine());

        int quantidadeEstoque = 0;
        boolean quantidadeValida = false;
        while (!quantidadeValida) {
            System.out.print("Quantidade do Estoque: ");
            try {
                quantidadeEstoque = Integer.parseInt(scan.nextLine());
                quantidadeValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        int precoEstoque = preco * quantidadeEstoque; //Coloca o preço do estoque de acordo com a quantidade que tem no estoque do produto x o preço da unidade

        Estoque estoque = new Estoque(quantidadeEstoque, precoEstoque);
        
        listaFornecedores();

        System.out.print("Nome do Fornecedor: ");
        String nomeFornecedor = scan.nextLine();

        Fornecedor fornecedor = null;
        for (Fornecedor forn : fornecedores) {
            if (forn.getNome().equalsIgnoreCase(nomeFornecedor)) {
                fornecedor = forn;
                break;
            }
        }

        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        Produto produto = new Produto(id, nome, descricao, fornecedor, estoque, preco, quantidadeEstoque, precoEstoque);

        produtos.add(produto); 
        countProd++;
        System.out.println("Produto cadastrado com sucesso.");
    }


    public void alteraProduto(Scanner scan) {
        System.out.print("Código do Produto a ser alterado: ");
        int codigo = scan.nextInt();
        scan.nextLine();

        Produto produtoParaAlterar = null;

        for (Produto produto : produtos) {
            if (produto.getId() == codigo) {
                produtoParaAlterar = produto;
                break;
            }
        }
        if (produtoParaAlterar == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo Nome: ");
        String novoNome = scan.nextLine();
        System.out.print("Nova Descrição: ");
        String novaDescricao = scan.nextLine();
        System.out.print("Novo Preço: ");
        double novoPreco = scan.nextDouble();
        scan.nextLine();

        produtoParaAlterar.setNome(novoNome);
        produtoParaAlterar.setDescricao(novaDescricao);
        produtoParaAlterar.setPreco(novoPreco);

        System.out.println("Produto alterado com sucesso.");
    }

    public void excluiProduto(Scanner scan) {
        System.out.print("Código do Produto a ser excluído: ");
        int codigo = scan.nextInt();
        scan.nextLine();

        for (Produto produto : produtos) {
            if (produto.getId() == codigo) {
                produtos.remove(produto);
                countProd--;
                System.out.println("Produto excluído com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }
    
    private void listaProdutos() {
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome());
        }
    }

    public void consultaProduto(Scanner scan) {
    	listaProdutos();
    	
        System.out.print("Código do Produto a ser consultado: ");
        int codigo = scan.nextInt();
        scan.nextLine();

        for (Produto produto : produtos) {
            if (produto.getId() == codigo) {
                System.out.println(produto);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private void manutencaoEstoque(Scanner scan) {
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│    Manutenção de Estoque:                   │");
        System.out.println("│    1 - Adicionar ao Estoque                 │");
        System.out.println("│    2 - Remover do Estoque                   │");
        System.out.println("│    0 - Voltar                               │");
        System.out.println("└─────────────────────────────────────────────┘");
        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1:
                adicionaEstoque(scan);
                break;
            case 2:
                removeEstoque(scan);
                break;
            case 0:
                System.out.println("Voltando ao menu administrador...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void adicionaEstoque(Scanner scan) {
    	listaProdutos();
    	
        System.out.print("Codigo do Produto: ");
        int codigo = scan.nextInt();
        scan.nextLine();

        Produto produto = null;

        for (Produto prod : produtos) {
            if (prod.getId() == codigo) {
                produto = prod;
                break;
            }
        }

        if (produto == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        System.out.print("Quantidade a adicionar: ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        produto.getEstoque().adicionaQuantidade(quantidade);
        System.out.println("Estoque atualizado com sucesso.");
    }

    private void removeEstoque(Scanner scan) {
    	listaProdutos();
    	
        System.out.print("Codigo do Produto: ");
        int codigo = scan.nextInt();
        scan.nextLine();

        Produto produto = null;

        for (Produto prod : produtos) {
            if (prod.getId() == codigo) {
                produto = prod;
                break;
            }
        }

        if (produto == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        System.out.print("Quantidade a retirar / remover : ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        if (produto.getEstoque().getQuantidade() < quantidade) { 
            System.out.println("Quantidade superior ao estoque disponivel");
        } else {
            produto.removeEstoque(quantidade);
            System.out.println("Estoque atualizado com sucesso.");
        }
    }
    
    private void menuCliente(Scanner scan) {
        int opcao = 0;

        do {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│    Menu Cliente:                            │");
            System.out.println("│    1 - Consultar Produtos                   │");
            System.out.println("│    2 - Fazer Pedido                         │");
            System.out.println("│    3 - consulta carrinho + fecha compra     │");
            System.out.println("│    4 - Consulta histórico de compras        │");
            System.out.println("│    0 - Voltar                               │");
            System.out.println("└─────────────────────────────────────────────┘");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                	 consultaProdutosPorNome(scan);
                    break;
                case 2:
                	fazerPedido(scan);
                    break;
                case 3:
                    consultarCarrinho(scan);
                    break;
                case 4:
                    consultarHistoricoCompras(scan);
                    break;
                case 5: 
                	criarCliente(scan);
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
    
    private void consultaProdutosPorNome(Scanner scan) {
    	listaProdutos();
    	
        System.out.print("Digite o nome do produto: ");
        String chave = scan.nextLine().toLowerCase(); 

        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(chave) || produto.getDescricao().toLowerCase().contains(chave)) {
                produtosEncontrados.add(produto);
            }
        }

        if (produtosEncontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado com o nome fornecido");
        } else {
            System.out.println("Produtos encontrados:");
            for (int i = 0; i < produtosEncontrados.size(); i++) {
                Produto produto = produtosEncontrados.get(i);
                System.out.println((i + 1) + ". ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Descrição: " + produto.getDescricao() + " | Quantidade no estoque " + produto.getEstoque().getQuantidade());
            }
        }
    }
    
    private void fazerPedido(Scanner scan) {
        listaProdutos();
        System.out.print("Digite o nome do produto que você deseja para concluir a busca: ");
        String nomeProduto = scan.nextLine().toLowerCase();
        List<Produto> produtosEncontrados = new ArrayList<>();
        
        // Busca por produtos que correspondem ao nome ou descrição fornecidos
        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(nomeProduto) || produto.getDescricao().toLowerCase().contains(nomeProduto)) {
                produtosEncontrados.add(produto);
            }
        }
        
        if (produtosEncontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado com a palavra-chave fornecida.");
        } else {
            System.out.println("Produtos encontrados:");
            for (int i = 0; i < produtosEncontrados.size(); i++) {
                Produto produto = produtosEncontrados.get(i);
                System.out.println((i + 1) + ". ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Descricao: " + produto.getDescricao() + " | Quantidade no estoque: " + produto.getEstoque().getQuantidade());
            }
            
            System.out.print("Digite o número do produto que você deseja selecionar: ");
            int escolhaProduto = scan.nextInt();
            scan.nextLine();
            
            if (escolhaProduto > 0 && escolhaProduto <= produtosEncontrados.size()) {
                Produto produtoSelecionado = produtosEncontrados.get(escolhaProduto - 1);
                
                // Verifica se a quantidade desejada é maior que a disponível em estoque
                int quantidadeDisponivel = produtoSelecionado.getEstoque().getQuantidade();
                System.out.print("Quantidade desejada (disponível: " + quantidadeDisponivel + "): ");
                int quantidadeDesejada = scan.nextInt();
                scan.nextLine();
                
                if (quantidadeDesejada > quantidadeDisponivel) {
                    System.out.println("A quantidade desejada é maior do que a quantidade disponível em estoque.");
                    System.out.print("Deseja prosseguir com a quantidade disponível? (s/n): ");
                    String resposta = scan.nextLine().trim().toLowerCase();
                    
                    if (!resposta.equals("s")) {
                        System.out.println("Pedido cancelado.");
                        return;
                    }
                    
                    quantidadeDesejada = quantidadeDisponivel;
                }
                
                fazerPedidoProduto(scan, produtoSelecionado, quantidadeDesejada);
            } else {
                System.out.println("Escolha inválida.");
            }
        }
    }

    private void fazerPedidoProduto(Scanner scan, Produto produto, int quantidadeDesejada) {
        System.out.println("Produto selecionado: " + produto.getNome());
        System.out.println("Quantidade desejada: " + quantidadeDesejada);
        
        double totalItem = quantidadeDesejada * produto.getPreco();
        System.out.println("Total do item: R$" + totalItem);
        
        System.out.print("Confirmar pedido (S/N)? ");
        String confirmacao = scan.nextLine();
        
        if (confirmacao.equalsIgnoreCase("S")) {
            Cliente cliente = criarOuBuscarCliente(scan);
            
            if (cliente != null) {
                adicionarAoCarrinho(produto, quantidadeDesejada, cliente, totalItem);
                
                // Atualiza o estoque somente se a quantidade desejada estiver disponível
                if (produto.getEstoque().getQuantidade() >= quantidadeDesejada) {
                    produto.removeEstoque(quantidadeDesejada);
                    System.out.println("Estoque atualizado após a compra.");
                } else {
                    System.out.println("Erro ao atualizar estoque: quantidade solicitada maior que a disponível.");
                }
            } else {
                System.out.println("Erro ao criar ou buscar cliente.");
            }
        } else {
            System.out.println("Pedido cancelado.");
        }
    }

    private Cliente criarOuBuscarCliente(Scanner scan) {
        System.out.println("Crie sua conta de cliente ou busque uma conta existente: ");
        
        System.out.println("Caso saiba que não possui conta, insira qualquer coisa no campo NOME abaixo");
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        Cliente clienteExistente = buscarClientePorNome(nome);
        if (clienteExistente != null) {
            System.out.println("Já existe um cliente com este nome:");
            System.out.println(clienteExistente);
            System.out.print("Este cliente é você (S/N)? ");
            String resposta = scan.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                return clienteExistente;
            }
            else if(resposta.equalsIgnoreCase("N")) {
            	return criarCliente(scan);
            }
        }

        
        return criarCliente(scan);
    }

    private Cliente criarCliente(Scanner scan) {
        System.out.println("Criando nova conta de cliente...");
        
        System.out.println("Nome: ");
        String nome = scan.nextLine();

        System.out.print("Telefone: ");
        long telefone = 0;
        boolean telefoneValido = false;
        while (!telefoneValido) {
            try {
                telefone = Long.parseLong(scan.nextLine());
                telefoneValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número de telefone válido.");
            }
        }

        String email;
        do {
            System.out.print("Email (deve terminar com '@gmail.com'): ");
            email = scan.nextLine();
        } while (!validaEmail(email));

        int cartao = 0;
        boolean cartaoValido = false;
        while (!cartaoValido) {
            System.out.print("Cartão do cliente: ");
            try {
                cartao = Integer.parseInt(scan.nextLine());
                cartaoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.println("Endereço:");
        System.out.print("Rua: ");
        String rua = scan.nextLine();

        int numero = 0;
        boolean numeroValidoCadastro = false;
        while (!numeroValidoCadastro) {
            System.out.print("Número do endereço: ");
            try {
                numero = Integer.parseInt(scan.nextLine());
                numeroValidoCadastro = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.print("Complemento: ");
        String complemento = scan.nextLine();
        System.out.print("Bairro: ");
        String bairro = scan.nextLine();

        int cep = 0;
        boolean cepValido = false;
        while (!cepValido) {
            System.out.print("CEP do endereço: ");
            try {
                cep = Integer.parseInt(scan.nextLine());
                cepValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }

        System.out.print("Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Estado: ");
        String estado = scan.nextLine();

        Endereco enderecoCliente = new Endereco(rua, numero, complemento, bairro, cep, cidade, estado);
        Cliente novoCliente = new Cliente(nome, telefone, email, cartao, enderecoCliente);
        clientes.add(novoCliente);  // add na lista de clientes
        return novoCliente;
    }

    private Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    private void adicionarAoCarrinho(Produto produto, int quantidade, Cliente cliente, double totalItem) {
        ItemPedido item = new ItemPedido(produto, quantidade);

        if (pedidos.isEmpty()) {
        	
            Pedido novoPedido = new Pedido(1, new Date(), cliente, new ArrayList<>(), "NOVO"); 
            novoPedido.getItens().add(item);
            pedidos.add(novoPedido);
        } else {
            Pedido ultimoPedido = pedidos.get(pedidos.size() - 1);
            if ("NOVO".equals(ultimoPedido.getStatus())) { 
            } else {
                Pedido novoPedido = new Pedido(pedidos.size() + 1, new Date(), cliente, new ArrayList<>(), "NOVO");
                novoPedido.getItens().add(item);
                pedidos.add(novoPedido);
            }
        }

        System.out.println("Item adicionado ao carrinho.");
    }
    
    private void consultarCarrinho(Scanner scan) {
        System.out.println("Consultando carrinho:");

        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scan.nextLine();

        Cliente cliente = null;

        for (Pedido pedido : pedidos) {
            if ("NOVO".equals(pedido.getStatus()) && pedido.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                cliente = pedido.getCliente();
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Carrinho não encontrado para o cliente '" + nomeCliente + "' ou o carrinho está vazio.");
        } else {
            Pedido carrinho = null;

            for (Pedido pedido : pedidos) {
                if ("NOVO".equals(pedido.getStatus()) && pedido.getCliente().equals(cliente)) {
                    carrinho = pedido;
                    break;
                }
            }

            if (carrinho == null || carrinho.getItens().isEmpty()) {
                System.out.println("O carrinho está vazio.");
            } else {
                double totalCarrinho = 0.0;
                for (ItemPedido item : carrinho.getItens()) {
                    double totalItem = item.getQuantidade() * item.getProduto().getPreco();
                    System.out.println("Produto: " + item.getProduto().getNome() +
                            " | Quantidade: " + item.getQuantidade() +
                            " | Preço unitário: R$" + item.getProduto().getPreco() +
                            " | Total do item: R$" + totalItem);
                    totalCarrinho += totalItem;
                }

                // Calcula o ICMS (17% do total do carrinho)
                double icms = totalCarrinho * 0.17;
                totalCarrinho += icms;

                System.out.println("Total do carrinho (incluindo 17% de ICMS): R$" + totalCarrinho);

                System.out.print("Deseja efetuar a compra (S/N), cancelar (C) ou reiniciar o carrinho (R)? ");
                String acao = scan.nextLine();
                if (acao.equalsIgnoreCase("S")) {
                    efetuarCompra(cliente); 
                } else if (acao.equalsIgnoreCase("C")) {
                    efetuarCancelamento(carrinho);
                    System.out.println("Carrinho cancelado.");
                } else if (acao.equalsIgnoreCase("R")) {
                    pedidos.remove(carrinho);
                    System.out.println("Carrinho reiniciado.");
                } else {
                    System.out.println("Ação cancelada.");
                }
            }
        }
    }

    
    private void efetuarCompra(Cliente cliente) {
        for (Pedido pedido : pedidos) {
            if ("NOVO".equals(pedido.getStatus()) && pedido.getCliente().equals(cliente)) {
                pedido.setStatus("ENTREGUE");
                pedido.setDataPedido(new Date());
                System.out.println("Compra efetuada com sucesso para o cliente " + cliente.getNome());
                return;
            }
        }
        System.out.println("Não há itens no carrinho para efetuar a compra para o cliente " + cliente.getNome());
    }
    
    private void efetuarCancelamento(Pedido carrinho) {
        carrinho.setStatus("CANCELADO");
    }


    private void consultarHistoricoCompras(Scanner scan) {
        System.out.println("Consultando histórico de compras:");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Consultar por nome do cliente");
        System.out.println("2 - Consultar por intervalo de datas");
        int opcao = scan.nextInt();
        scan.nextLine();
        switch (opcao) {
            case 1:
                System.out.print("Digite o nome do cliente: ");
                String nomeCliente = scan.nextLine();
                consultarPorNomeCliente(nomeCliente);
                break;
            case 2:
                System.out.print("Digite a data inicial (yyyy-MM-dd): ");
                String dataInicialStr = scan.nextLine();
                System.out.print("Digite a data final (yyyy-MM-dd): ");
                String dataFinalStr = scan.nextLine();
                consultarPorIntervaloDatas(dataInicialStr, dataFinalStr);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
    
    private void consultarPorNomeCliente(String nomeCliente) {
        Cliente cliente = null;
        for (Pedido pedido : pedidos) {
            if (("ENTREGUE".equals(pedido.getStatus()) || "CANCELADO".equals(pedido.getStatus())) && pedido.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                cliente = pedido.getCliente();
                break;
            }
        }
        if (cliente == null) {
            System.out.println("Histórico de compras não encontrado para o cliente '" + nomeCliente + "'.");
        } else {
            exibirHistoricoCliente(cliente);
        }
    }
    
    private void consultarPorIntervaloDatas(String dataInicialStr, String dataFinalStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Para evitar datas inválidas
        try {
            Date dataInicial = sdf.parse(dataInicialStr);
            Date dataFinal = sdf.parse(dataFinalStr);
            System.out.println("Histórico de Compras no intervalo de " + sdf.format(dataInicial) + " a " + sdf.format(dataFinal) + ":");
            boolean encontrouPedidos = false;
            for (Pedido pedido : pedidos) {
                Date dataPedido = pedido.getDataPedido();
                if (("ENTREGUE".equals(pedido.getStatus()) || "CANCELADO".equals(pedido.getStatus())) &&
                        !dataPedido.before(dataInicial) && !dataPedido.after(dataFinal)) {
                    encontrouPedidos = true;
                    exibirDetalhesPedido(pedido);
                }
            }
            if (!encontrouPedidos) {
                System.out.println("Nenhum pedido encontrado no intervalo de datas especificado.");
            }
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use o formato yyyy-MM-dd.");
        }
    }
    
    private void exibirHistoricoCliente(Cliente cliente) {
        System.out.println("Histórico de Compras para o cliente " + cliente.getNome() + ":");
        for (Pedido pedido : pedidos) {
            if (("ENTREGUE".equals(pedido.getStatus()) || "CANCELADO".equals(pedido.getStatus())) && pedido.getCliente().equals(cliente)) {
                exibirDetalhesPedido(pedido);
            }
        }
    }
    
    private void exibirDetalhesPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getNumero() + " | Data: " + pedido.getDataPedido() + " | Status: " + pedido.getStatus());
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("Produto: " + item.getProduto().getNome() + " | Quantidade: " + item.getQuantidade());
        }
        System.out.println("------------------------");
    }

}
