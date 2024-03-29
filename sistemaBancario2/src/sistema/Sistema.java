package sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
  Esta Ã© a classe principal do sistema. Ela possui ArrayList de objetos
  Pessoa e objetos da classe Banco. Ã‰ nessa classe que temos
  o menu que nos permite criar os bancos e as pessoas. Note que uma pessoa
  sÃ³ se torna um cliente de uma agÃªncia a partir do momento que ela Ã© associada
  a uma conta bancÃ¡ria
*/

public class Sistema implements Serializable{ // pode ser salvo e lido a partir de um arquivo
  private ArrayList<Pessoa> pessoas = new ArrayList<>(); // ArrayList de pessoas
  private ArrayList<Banco> bancos = new ArrayList<>();   // ArrayList de bancos  
  
  public static void main(String[] args) throws Exception {
    // chama o menu principal do sistema
    Sistema sistema = new Sistema();
    sistema = carregarDadosArquivo();
    sistema.menuPrincipal();
  }
  
  // mÃ©todo que exibe o menu principal do sistema
  public void menuPrincipal(){
    Scanner entrada = new Scanner(System.in);
    
    while(true){  
      limparTela();
      System.out.println("\n:: S I S T E M A   B A N C Ã� R I O ::\n");
      System.out.println("Bem-vindo(a) ao sistem. Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Administrar o Sistema");
      System.out.println("2 - Acessar como Cliente");
      System.out.println("3 - Sair");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1:
          menuAdministrarSistema(); // chama o menu de administraÃ§Ã£o
          break;
        case 2:
          menuCliente(); // chama o menu do cliente
          break;
        case 3:
          System.out.println("\nObrigado por usar o Sistema BancÃ¡rio\n");  
          System.exit(0);
      }
    }
  }
  
  // menu que permite administrar o sistema
  public int menuAdministrarSistema(){
    Scanner entrada = new Scanner(System.in);
    
    while(true){ // exibe continuamente o menu de opÃ§Ãµes
      limparTela(); // tire se nÃ£o limpar a tela no seu sistema operacional
      System.out.println("\n:: A D M I N I S T R A Ã‡ Ãƒ O  D O  S I S T E M A ::\n");
      System.out.println("Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Gerenciar Bancos");
      System.out.println("2 - Gerenciar Pessoas");
      System.out.println("3 - Gerenciar AgÃªncias");
      System.out.println("4 - Gerenciar Contas");
      System.out.println("5 - Voltar Menu Anterior");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1: // vamos gerenciar os bancos
          menuGerenciarBancos();
          break;
        case 2: // vamos gerenciar as pessoas (que serÃ£o futuros clientes)
          menuGerenciarPessoas();
          break;
        case 3: // vamos gerenciar as agÃªncias de um determinado banco
          menuGerenciarAgencias();
          break;
        case 4: // vamos gerenciar as contas de uma determinada agÃªncia de um determinado banco
          menuGerenciarContas();
          break;
        case 5:
          return 1; // volta para o menu principal
      }  
    }
  }
  
  // menu para cadatrar, listar, excluir e atualizar os bancos
  public int menuGerenciarBancos(){
    Scanner entrada = new Scanner(System.in);
    Banco temp; // serve para vÃ¡rias operaÃ§Ãµes neste menu
    String pesquisaBanco; // serve para as pesquisas dos bancos
    
    while(true){ // mostra o menu de forma repetitiva atÃ© o usuÃ¡rio usar a opÃ§Ã£o de sair
      limparTela(); // tire caso nÃ£o funcionar no seu sistema operacional
      System.out.println("\n:: G E R E N C I A R   B A N C O S ::\n");
      System.out.println("Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Novo Banco");
      System.out.println("2 - Listar Bancos");
      System.out.println("3 - Pesquisar Banco");
      System.out.println("4 - Excluir Banco");
      System.out.println("5 - Atualizar Banco");
      System.out.println("6 - Voltar Menu Anterior");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1: // vamos cadastrar um novo banco
          System.out.print("\nNÃºmero do Banco: ");
          String numeroBanco = entrada.nextLine();
          System.out.print("Nome do Banco: ");
          String nomeBanco = entrada.nextLine();
        
          // vamos incrementar o contador de bancos
          Banco.contadorBancos++;
        
          // agora vamos criar um novo objeto da classe Banco
          Banco b = new Banco(Banco.contadorBancos, nomeBanco, numeroBanco);
          // e o adicionamos no ArrayList de bancos
          bancos.add(b);
        
          // e finalmente mostramos uma mensagem de sucesso.
          System.out.println("\nO banco foi criado com sucesso");
          salvarDadosArquivo(); // salva os dados no arquivo
        
          break;  
        
        case 2: // vamos listar os bancos cadastrados
          if(bancos.isEmpty()){
            System.out.println("\nNÃ£o hÃ¡ nenhum banco cadastrado.");  
          }
          else{
            for(int i = 0; i < bancos.size(); i++){
              temp = bancos.get(i); // obtÃ©m o banco da iteraÃ§Ã£o atual
              System.out.println("\nId: " + temp.getId());
              System.out.println("NÃºmero: " + temp.getNumero());
              System.out.println("Nome: " + temp.getNome());
              System.out.println("Quant AgÃªncias: " + temp.getAgencias().size());
            }  
          }
          
          break;
        
        case 3: // vamos pesquisar um banco
          System.out.print("\nInforme o id, nÃºmero ou nome do Banco: ");
          pesquisaBanco = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa o banco
          temp = pesquisarBanco(pesquisaBanco);
          if(temp == null){ // banco nÃ£o encotrado
            System.out.println("\nO banco nÃ£o foi encontrado.");  
          }
          else{
            // mostra o banco encontrado
            System.out.println("\nId: " + temp.getId());
            System.out.println("NÃºmero: " + temp.getNumero());
            System.out.println("Nome: " + temp.getNome());
            System.out.println("Quant AgÃªncias: " + temp.getAgencias().size());
          }
        
          break;    
          
        case 4: // vamos excluir um banco
          System.out.print("\nInforme o id, nÃºmero ou nome do Banco a ser excluÃ­do: ");
          pesquisaBanco = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa o banco
          temp = pesquisarBanco(pesquisaBanco);
          if(temp == null){ // banco nÃ£o encotrado
            System.out.println("\nO banco nÃ£o foi encontrado.");  
          }
          else{
            // vamos excluir este banco. AtenÃ§Ã£o. Ao excluir um banco, todas
            // suas agÃªncias e contas serÃ£o excluÃ­das tambÃ©m
            bancos.remove(temp);
            System.out.println("\nBanco excluÃ­do com sucesso.");
            salvarDadosArquivo(); // salva os dados no arquivo
          }
        
          break;  
          
        case 5: // vamos atualizar um banco
          System.out.print("\nInforme o id, nÃºmero ou nome do Banco a ser atualizado: ");
          pesquisaBanco = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa o banco
          temp = pesquisarBanco(pesquisaBanco);
          if(temp == null){ // banco nÃ£o encotrado
            System.out.println("\nO banco nÃ£o foi encontrado.");  
          }
          else{
            // mostra o banco encontrado
            System.out.println("\nDados atuais deste banco:");
            System.out.println("\nId: " + temp.getId());
            System.out.println("NÃºmero: " + temp.getNumero());
            System.out.println("Nome: " + temp.getNome());
            System.out.println("Quant AgÃªncias: " + temp.getAgencias().size());
            
            System.out.println("\nInforme os novos dados:");
            System.out.print("\nNovo NÃºmero do Banco: ");
            String novoNumeroBanco = entrada.nextLine();
            System.out.print("Novo Nome do Banco: ");
            String novoNomeBanco = entrada.nextLine();
            
            // vamos atualizar os dados deste banco no ArrayList
            temp.setNome(novoNomeBanco);
            temp.setNumero(novoNumeroBanco);
            System.out.println("\nBanco atualizado com sucesso.");
            salvarDadosArquivo(); // salva os dados no arquivo
          }
        
          break;  
          
        case 6:
          return 0; // volta para o menu principal
      }
    }
  }
  
  // mÃ©todo que pesquisa um banco pelo id, nÃºmero ou nome e retorna um objeto da classe Banco
  public Banco pesquisarBanco(String pesquisaBanco){
    Banco b = null;
    
    // este banco existe?
    for(int i = 0; i < bancos.size(); i++){
      // pesquisa pelo id
      if(Integer.toString(bancos.get(i).getId()).equals(pesquisaBanco)){
        return bancos.get(i);  
      }
      // pesquisar por nome
      else if(bancos.get(i).getNome().contains(pesquisaBanco)){
        return bancos.get(i);  
      }
      // pesquisar pelo nÃºmero
      else if(bancos.get(i).getNumero().contains(pesquisaBanco)){
        return bancos.get(i);  
      }
    }
    
    return b;
  }
  
  // menu para cadatrar, listar, pesquisar, excluir e atualizar as pessoas (futuros clientes)
  public int menuGerenciarPessoas(){
    Scanner entrada = new Scanner(System.in);
    Pessoa temp; // serve para vÃ¡rias operaÃ§Ãµes neste menu
    String pesquisaPessoa; // serve para as pesquisas das pessoas
    
    while(true){ // mostra o menu de forma repetitiva atÃ© o usuÃ¡rio usar a opÃ§Ã£o de sair
      limparTela(); // tire caso nÃ£o funcionar no seu sistema operacional
      System.out.println("\n:: G E R E N C I A R   P E S S O A S ::\n");
      System.out.println("Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Nova Pessoa (Futuro Cliente)");
      System.out.println("2 - Listar Pessoas");
      System.out.println("3 - Pesquisar Pessoa");
      System.out.println("4 - Excluir Pessoa");
      System.out.println("5 - Atualizar Pessoa");
      System.out.println("6 - Voltar Menu Anterior");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1: // vamos cadastrar uma nova pessoa
          System.out.print("\nNome: ");
          String nomePessoa = entrada.nextLine();
          System.out.print("Idade: ");
          int idadePessoa = Integer.parseInt(entrada.nextLine());
          System.out.print("Sexo: ");
          char sexoPessoa = entrada.nextLine().charAt(0);
        
          // vamos incrementar o contador de pessoas
          Pessoa.contadorPessoas++;
        
          // agora vamos criar um novo objeto da classe Pessoa
          Pessoa p = new Pessoa(Pessoa.contadorPessoas, nomePessoa, idadePessoa, sexoPessoa);
          // e o adicionamos no ArrayList de pessoas
          pessoas.add(p);
        
          // e finalmente mostramos uma mensagem de sucesso.
          System.out.println("\nA pessoa foi criada com sucesso");
        
          break;  
        
        case 2: // vamos listar as pessoas cadastradas
          if(pessoas.isEmpty()){
            System.out.println("\nNÃ£o hÃ¡ nenhuma pessoa cadastrada.");  
          }
          else{
            for(int i = 0; i < pessoas.size(); i++){
              temp = pessoas.get(i); // obtÃ©m a pessoa da iteraÃ§Ã£o atual
              System.out.println("\nId: " + temp.getId());
              System.out.println("Nome: " + temp.getNome());
              System.out.println("Idade: " + temp.getIdade());
              System.out.println("Sexo: " + temp.getSexo());
              System.out.println("Quant Contas BancÃ¡rias: " + temp.getContas(bancos).size());
            }  
          }
          break;
        
        case 3: // vamos pesquisar uma pessoa
          System.out.print("\nInforme o id ou nome da pessoa: ");
          pesquisaPessoa = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a pessoa
          temp = pesquisarPessoa(pesquisaPessoa);
          if(temp == null){ // pessoa nÃ£o encotrado
            System.out.println("\nA pessoa nÃ£o foi encontrada.");  
          }
          else{
            // mostra a pessoa encontrada
            System.out.println("\nId: " + temp.getId());
            System.out.println("Nome: " + temp.getNome());
            System.out.println("Idade: " + temp.getIdade());
            System.out.println("Sexo: " + temp.getSexo());
            System.out.println("Quant Contas BancÃ¡rias: " + temp.getContas(bancos).size());
          }
        
          break;    
          
        case 4: // vamos excluir uma pessoa
          System.out.print("\nInforme o id ou nome da pessoa a ser excluÃ­da: ");
          pesquisaPessoa = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a pessoa
          temp = pesquisarPessoa(pesquisaPessoa);
          if(temp == null){ // pessoa nÃ£o encontrada
            System.out.println("\nA pessoa nÃ£o foi encontrada.");  
          }
          else{
            // vamos excluir esta pessoa. AtenÃ§Ã£o: Se houver contas bancÃ¡rias relacionadas
            // a esta pessoa, entÃ£o a exclusÃ£o da conta bancÃ¡ria deverÃ¡ ser feita primeiro
            if(temp.getContas(bancos).size() > 0){
              System.out.println("\nOps! Esta pessoa possui contas bancÃ¡rias. Exclua as contas primeiro.");  
            }
            else{
              pessoas.remove(temp);
              System.out.println("\nPessoa excluÃ­da com sucesso.");
            }
          }
        
          break;  
          
        case 5: // vamos atualizar uma pessoa
          System.out.print("\nInforme o id ou nome da pessoa a ser atualizada: ");
          pesquisaPessoa = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a pessoa
          temp = pesquisarPessoa(pesquisaPessoa);
          if(temp == null){ // pessoa nÃ£o encotrado
            System.out.println("\nA pessoa nÃ£o foi encontrada.");  
          }
          else{
            // mostra a pessoa encontrada
            System.out.println("\nDados atuais desta pessoa:");
            System.out.println("\nId: " + temp.getId());
            System.out.println("Nome: " + temp.getNome());
            System.out.println("Idade: " + temp.getIdade());
            System.out.println("Sexo: " + temp.getSexo());
            System.out.println("Quant Contas BancÃ¡rias: " + temp.getContas(bancos).size());
            
            System.out.println("\nInforme os novos dados:");
            System.out.print("\nNovo Nome: ");
            String novoNomePessoa = entrada.nextLine();
            System.out.print("Nova Idade: ");
            int novaIdadePessoa = Integer.parseInt(entrada.nextLine());
            System.out.print("Novo Sexo: ");
            char novoSexoPessoa = entrada.nextLine().charAt(0);
            
            // vamos atualizar os dados desta pessoa no ArrayList
            temp.setNome(novoNomePessoa);
            temp.setIdade(novaIdadePessoa);
            temp.setSexo(novoSexoPessoa);
            System.out.println("\nPessoa atualizada com sucesso.");
          }
        
          break;  
          
        case 6:
          return 0; // volta para o menu principal
      }
    }
  }
  
  // mÃ©todo que pesquisa uma pessoa pelo id, nÃºmero ou nome e retorna um objeto da classe Pessoa
  public Pessoa pesquisarPessoa(String pesquisaPessoa){
    Pessoa p = null;
    
    // esta pessoa existe?
    for(int i = 0; i < pessoas.size(); i++){
      // pesquisa pelo id
      if(Integer.toString(pessoas.get(i).getId()).equals(pesquisaPessoa)){
        return pessoas.get(i);  
      }
      // pesquisar por nome
      else if(pessoas.get(i).getNome().contains(pesquisaPessoa)){
        return pessoas.get(i);  
      }
    }
    
    return p;
  }
  
  // menu para cadatrar, listar, pesquisar, excluir e atualizar as agÃªncias
  public int menuGerenciarAgencias(){
    Scanner entrada = new Scanner(System.in);
    Agencia temp; // serve para vÃ¡rias operaÃ§Ãµes neste menu
    String pesquisaAgencia; // serve para as pesquisas das agÃªncias
    Banco bancoAtual = null; // guarda o banco atual 
    
    // para gerenciar uma agÃªncia nÃ³s precisamos de um banco
    while(bancoAtual == null){
      System.out.print("\nInforme o id, nÃºmero ou nome do banco: ");
      String pesquisaBanco = entrada.nextLine();
      // chamamos o mÃ©todo que pesquisa o banco
      Banco b = pesquisarBanco(pesquisaBanco);
      if(b == null){ // banco nÃ£o encotrado
        System.out.print("\nO banco nÃ£o foi encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
        int opcao = Integer.parseInt(entrada.nextLine());
        if(opcao == 2){
          return 1; // saÃ­mos daqui e voltamos para o menu anterior  
        }
      }
      else{
        // banco encontrado. Vamos prosseguir com as agÃªncias
        bancoAtual = b;
      }
    }
    
    // AtenÃ§Ã£o: o menu abaixo deverÃ¡ ser exibido somente se um banco for selecionado
    
    while(true){ // mostra o menu de forma repetitiva atÃ© o usuÃ¡rio usar a opÃ§Ã£o de sair
      limparTela(); // tire caso nÃ£o funcionar no seu sistema operacional
      System.out.println("\n:: G E R E N C I A R   A G ÃŠ N C I A S ::\n");
      System.out.println("Banco selecionado: " + bancoAtual.getNome() + "\n");
      
      System.out.println("Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Nova AgÃªncia");
      System.out.println("2 - Listar AgÃªncias");
      System.out.println("3 - Pesquisar AgÃªncia");
      System.out.println("4 - Excluir AgÃªncia");
      System.out.println("5 - Atualizar AgÃªncia");
      System.out.println("6 - Voltar Menu Anterior");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1: // vamos cadastrar uma nova agÃªncia
          System.out.print("\nNÃºmero da AgÃªncia: ");
          String numeroAgencia = entrada.nextLine();
          System.out.print("Cidade/Estado da AgÃªncia: ");
          String cidadeAgencia = entrada.nextLine();
        
          // vamos incrementar o contador de agÃªncias
          Agencia.contadorAgencias++;
        
          // agora vamos criar um novo objeto da classe Agencia
          Agencia a = new Agencia(bancoAtual, Agencia.contadorAgencias, numeroAgencia, cidadeAgencia);
          // e o adicionamos no ArrayList de agencias do banco selecionado
          bancoAtual.getAgencias().add(a);
        
          // e finalmente mostramos uma mensagem de sucesso.
          System.out.println("\nA AgÃªncia foi criada com sucesso");
        
          break;  
          
        case 2: // vamos listar as agÃªncias cadastradas no banco selecionado
          if(bancoAtual.getAgencias().isEmpty()){
            System.out.println("\nNÃ£o hÃ¡ nenhuma agÃªncia cadastrada neste banco.");  
          }
          else{
            for(int i = 0; i < bancoAtual.getAgencias().size(); i++){
              temp = bancoAtual.getAgencias().get(i); // obtÃ©m a agÃªncia da iteraÃ§Ã£o atual
              System.out.println("\nId: " + temp.getId());
              System.out.println("NÃºmero: " + temp.getNumero());
              System.out.println("Cidade/Estado: " + temp.getCidade());
              System.out.println("Quant Contas BancÃ¡rias: " + temp.getContas().size());
            }  
          }
          
          break;
          
        case 3: // vamos pesquisar uma agÃªncia
          System.out.print("\nInforme o id, nÃºmero ou cidade da agÃªncia: ");
          pesquisaAgencia = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a agÃªncia
          temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
          if(temp == null){ // agÃªncia nÃ£o encotrada
            System.out.println("\nA agÃªncia nÃ£o foi encontrada.");  
          }
          else{
            // mostra a agÃªncia encontrada
            System.out.println("\nId: " + temp.getId());
            System.out.println("NÃºmero: " + temp.getNumero());
            System.out.println("Cidade/Estado: " + temp.getCidade());
            System.out.println("Quant Contas BancÃ¡rias: " + temp.getContas().size());
          }
        
          break;    
        
        case 4: // vamos excluir uma agÃªncia
          System.out.print("\nInforme o id, nÃºmero ou cidade da agÃªncia: ");
          pesquisaAgencia = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a agÃªncia
          temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
          if(temp == null){ // agÃªncia nÃ£o encotrada
            System.out.println("\nA agÃªncia nÃ£o foi encontrada.");  
          }
          else{
            // vamos excluir esta agÃªncia. AtenÃ§Ã£o. Ao excluir uma agÃªncia, todas
            // suas contas serÃ£o excluÃ­das tambÃ©m
            bancoAtual.getAgencias().remove(temp);
            System.out.println("\nAgÃªncia excluÃ­da com sucesso.");
          }
        
          break;  
          
        case 5: // vamos atualizar uma agÃªncia
          System.out.print("\nInforme o id, nÃºmero ou cidade da agÃªncia: ");
          pesquisaAgencia = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a agÃªncia
          temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
          if(temp == null){ // agÃªncia nÃ£o encotrada
            System.out.println("\nA agÃªncia nÃ£o foi encontrada.");  
          }
          else{
            // mostra a agÃªncia encontrada
            System.out.println("\nDados atuais desta agÃªncia:");
            System.out.println("\nId: " + temp.getId());
            System.out.println("NÃºmero: " + temp.getNumero());
            System.out.println("Cidade/Estado: " + temp.getCidade());
            System.out.println("Quant Contas BancÃ¡rias: " + temp.getContas().size());
            
            System.out.println("\nInforme os novos dados:");
            System.out.print("\nNovo NÃºmero da AgÃªncia: ");
            String novoNumeroAgencia = entrada.nextLine();
            System.out.print("Nova Cidade/Estado da AgÃªncia: ");
            String novaCidadeAgencia = entrada.nextLine();
            
            // vamos atualizar os dados desta agÃªncia no ArrayList do banco atual
            temp.setNumero(novoNumeroAgencia);
            temp.setCidade(novaCidadeAgencia);
            System.out.println("\nAgÃªncia atualizada com sucesso.");
          }
        
          break;
          
        case 6:
          return 0; // volta para o menu principal
      }
    }
  }
  
  // mÃ©todo que pesquisa uma agÃªncia pelo id, nÃºmero ou cidade e retorna um objeto da classe Agencia
  public Agencia pesquisarAgencia(Banco b, String pesquisaAgencia){
    Agencia a = null;
    
    // esta agÃªncia existe?
    for(int i = 0; i < b.getAgencias().size(); i++){
      // pesquisa a agÃªncia pelo id
      if(Integer.toString(b.getAgencias().get(i).getId()).equals(pesquisaAgencia)){
        return b.getAgencias().get(i);  
      }
      // pesquisar por nÃºmero da agÃªncia
      else if(b.getAgencias().get(i).getNumero().contains(pesquisaAgencia)){
        return b.getAgencias().get(i);  
      }
      // pesquisar pela cidade da agÃªncia
      else if(b.getAgencias().get(i).getCidade().contains(pesquisaAgencia)){
        return b.getAgencias().get(i);  
      }
    }
    
    return a;
  }
  
  // menu para cadatrar, listar, pesquisar, excluir e atualizar as contas
  public int menuGerenciarContas(){
    Scanner entrada = new Scanner(System.in);
    Conta temp; // serve para vÃ¡rias operaÃ§Ãµes neste menu
    String pesquisaConta; // serve para as pesquisas das contas
    Banco bancoAtual = null; // guarda o banco atual
    Agencia agenciaAtual = null; // guarda a agÃªncia atual
    
    // para gerenciar uma conta nÃ³s precisamos primeiro de um banco
    while(bancoAtual == null){
      System.out.print("\nInforme o id, nÃºmero ou nome do banco: ");
      String pesquisaBanco = entrada.nextLine();
      // chamamos o mÃ©todo que pesquisa o banco
      Banco b = pesquisarBanco(pesquisaBanco);
      if(b == null){ // banco nÃ£o encotrado
        System.out.print("\nO banco nÃ£o foi encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
        int opcao = Integer.parseInt(entrada.nextLine());
        if(opcao == 2){
          return 1; // saÃ­mos daqui e voltamos para o menu anterior  
        }
      }
      else{
        // banco encontrado. Vamos prosseguir com as agÃªncias
        bancoAtual = b;
      }
    }
    
    // agora que jÃ¡ temos o banco, vamos selecionar a agÃªncia
    while(agenciaAtual == null){
      System.out.print("\nInforme o id, nÃºmero ou cidade da agÃªncia: ");
      String pesquisaAgencia = entrada.nextLine();
      // chamamos o mÃ©todo que pesquisa a agÃªncia
      Agencia a = pesquisarAgencia(bancoAtual, pesquisaAgencia);
      if(a == null){ // agÃªncia nÃ£o encotrada
        System.out.print("\nA AgÃªncia nÃ£o foi encontrada.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
        int opcao = Integer.parseInt(entrada.nextLine());
        if(opcao == 2){
          return 1; // saÃ­mos daqui e voltamos para o menu anterior  
        }
      }
      else{
        // agÃªncia encontrada. Vamos prosseguir com as contas
        agenciaAtual = a;
      }
    }
    
    // AtenÃ§Ã£o: o menu abaixo deverÃ¡ ser exibido somente se um banco e uma agÃªncia forem selecionados
    
    while(true){ // mostra o menu de forma repetitiva atÃ© o usuÃ¡rio usar a opÃ§Ã£o de sair
      limparTela(); // tire caso nÃ£o funcionar no seu sistema operacional
      System.out.println("\n:: G E R E N C I A R   C O N T A S ::\n");
      System.out.println("Banco selecionado: " + bancoAtual.getNome());
      System.out.println("AgÃªncia selecionada: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade() + "\n");
      
      System.out.println("Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Abertura de Nova Conta");
      System.out.println("2 - Listar Contas");
      System.out.println("3 - Pesquisar Conta");
      System.out.println("4 - Excluir Conta");
      System.out.println("5 - Atualizar Conta");
      System.out.println("6 - Voltar Menu Anterior");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1: // vamos abrir/cadastrar uma nova conta
          System.out.print("\nNÃºmero da Conta: ");
          String numeroConta = entrada.nextLine();
          System.out.print("Limite da Conta: ");
          double limiteConta = Double.parseDouble(entrada.nextLine());
          
          // para abrir uma nova conta nÃ³s precisamos de um cliente
          Pessoa cliente = null; // o cliente para o qual uma nova conta serÃ¡ aberta
          while(cliente == null){
            System.out.print("Informe o id ou nome do cliente: ");
            String pesquisaPessoa = entrada.nextLine();
            // chamamos o mÃ©todo que pesquisa a pessoa
            cliente = pesquisarPessoa(pesquisaPessoa);
            if(cliente == null){ // pessoa nÃ£o encotrado
              System.out.print("\nCliente nÃ£o encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
              int opcaoTemp = Integer.parseInt(entrada.nextLine());
              if(opcaoTemp == 2){
                return 1; // saÃ­mos daqui e voltamos para o menu anterior  
              }
            }
          }
        
          // vamos incrementar o contador de contas
          Conta.contadorContas++;
        
          // agora vamos criar um novo objeto da classe Conta
          Conta c = new Conta(agenciaAtual, cliente,  Conta.contadorContas, numeroConta, 0.0, limiteConta);
          // e o adicionamos no ArrayList da agÃªncia selecionada do banco selecionado
          agenciaAtual.getContas().add(c);
          
          // e finalmente mostramos uma mensagem de sucesso.
          System.out.println("\nUma nova conta foi criada para o cliente: " + cliente.getNome() 
            + "\ncom saldo inicial de R$ 0,00 e limite inicial de R$ " + limiteConta);
        
          break;  
        
        case 2: // vamos listar as contas cadastradas para a agÃªncia e banco selecionado
          if(agenciaAtual.getContas().isEmpty()){
            System.out.println("\nNÃ£o hÃ¡ nenhuma conta cadastrada nesta agÃªncia.");  
          }
          else{
            for(int i = 0; i < agenciaAtual.getContas().size(); i++){
              temp = agenciaAtual.getContas().get(i); // obtÃ©m a conta da iteraÃ§Ã£o atual
              System.out.println("\nId da conta bancÃ¡ria: " + temp.getId());
              System.out.println("NÃºmero da conta: " + temp.getNumero());
              System.out.println("Cliente: " + temp.getCliente().getNome());
              System.out.println("AgÃªncia: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade());
              System.out.println("Banco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
              System.out.println("Saldo atual: " + temp.getSaldo());
              System.out.println("Limite atual: " + temp.getLimite());
            }  
          }
          
          break;
        
        case 3: // vamos pesquisar uma conta
          System.out.print("\nInforme o id, nÃºmero ou nome do cliente da conta: ");
          pesquisaConta = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a conta
          temp = pesquisarConta(agenciaAtual, pesquisaConta);
          if(temp == null){ // conta nÃ£o encotrada
            System.out.println("\nA conta nÃ£o foi encontrada nesta agÃªncia.");  
          }
          else{
            // mostra a conta encontrada
            System.out.println("\nId da conta bancÃ¡ria: " + temp.getId());
            System.out.println("NÃºmero da conta: " + temp.getNumero());
            System.out.println("Cliente: " + temp.getCliente().getNome());
            System.out.println("AgÃªncia: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade());
            System.out.println("Banco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
            System.out.println("Saldo atual: " + temp.getSaldo());
            System.out.println("Limite atual: " + temp.getLimite());
          }
        
          break;    
        
        case 4: // vamos excluir uma conta
          System.out.print("\nInforme o id, nÃºmero ou nome do cliente da conta: ");
          pesquisaConta = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a conta
          temp = pesquisarConta(agenciaAtual, pesquisaConta);
          if(temp == null){ // conta nÃ£o encotrada
            System.out.println("\nA conta nÃ£o foi encontrada nesta agÃªncia.");  
          }
          else{
            // vamos excluir esta conta desta agÃªncia.
            agenciaAtual.getContas().remove(temp);
            System.out.println("\nConta excluÃ­da com sucesso.");
          }
        
          break;  
      
        case 5: // vamos atualizar uma conta
          System.out.print("\nInforme o id, nÃºmero ou nome do cliente da conta: ");
          pesquisaConta = entrada.nextLine();
          // chamamos o mÃ©todo que pesquisa a conta
          temp = pesquisarConta(agenciaAtual, pesquisaConta);
          if(temp == null){ // conta nÃ£o encotrada
            System.out.println("\nA conta nÃ£o foi encontrada.");  
          }
          else{
            // mostra a conta encontrada
            System.out.println("\nDados atuais desta agÃªncia:");
            System.out.println("\nId: " + temp.getId());
            System.out.println("NÃºmero da conta: " + temp.getNumero());
            System.out.println("Cliente: " + temp.getCliente().getNome());
            System.out.println("AgÃªncia: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade());
            System.out.println("Banco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
            System.out.println("Saldo atual: " + temp.getSaldo());
            System.out.println("Limite atual: " + temp.getLimite());
            
            System.out.println("\nInforme os novos dados da conta:");
            System.out.print("\nNovo NÃºmero da Conta: ");
            String novoNumeroConta = entrada.nextLine();
            System.out.print("Novo Limite da Conta: ");
            double novoLimiteConta = Double.parseDouble(entrada.nextLine());
            
            // vamos atualizar os dados desta conta no ArrayList da agÃªncia atual
            temp.setNumero(novoNumeroConta);
            temp.setLimite(novoLimiteConta);
            System.out.println("\nConta atualizada com sucesso.");
          }
        
          break;
          
        case 6:
          return 0; // volta para o menu principal
      }
    }
  }
  
  // mÃ©todo que pesquisa uma conta pelo id, nÃºmero ou nome do cliente e retorna um objeto da classe Conta
  public Conta pesquisarConta(Agencia agencia, String pesquisaConta){
    Conta c = null;
       
    // esta conta existe?
    for(int i = 0; i < agencia.getContas().size(); i++){
      // pesquisa a conta pelo id
      if(Integer.toString(agencia.getContas().get(i).getId()).equals(pesquisaConta)){
        return agencia.getContas().get(i);  
      }
      // pesquisar por nÃºmero da conta
      else if(agencia.getContas().get(i).getNumero().contains(pesquisaConta)){
        return agencia.getContas().get(i);  
      }
      // pesquisar pelo nome do cliente
      else if(agencia.getContas().get(i).getCliente().getNome().contains(pesquisaConta)){
        return agencia.getContas().get(i);  
      }
    }
    
    return c;
  }
  
  // mÃ©todo que pesquisa uma conta somente pelo id
  public Conta pesquisarContaPorId(int id){
    Conta c = null;
       
    // A pesquisa por id tem que percorrer todo o sistema bancÃ¡rio
    for(int i = 0; i < bancos.size(); i++){
      // agora percorremos as agÃªncias de cada banco
      Banco banco = bancos.get(i); // representa o banco da iteraÃ§Ã£o atual
      for(int j = 0; j < banco.getAgencias().size(); j++){
        Agencia agencia = banco.getAgencias().get(j); // agÃªncia da iteraÃ§Ã£o atual
        for(int x = 0; x < agencia.getContas().size(); x++){
          Conta conta = agencia.getContas().get(x); // conta da iteraÃ§Ã£o atual
          if(conta.getId() == id){
            return conta;  
          }
        }
      }
    }
    
    return c;
  }
  
  // menu que permite o acesso do cliente, mediante informaÃ§Ã£o de seus dados
  public int menuCliente(){
    Scanner entrada = new Scanner(System.in);
    int idConta; // vai servir para as operaÃ§Ãµes nas contas 
    Conta conta; // para operaÃ§Ãµes nas contas tambÃ©m
    Transacao transacao; // para gerar o extrato das contas
    NumberFormat nf = NumberFormat.getCurrencyInstance(); // para formatar a moeda
    Format formato = new SimpleDateFormat("dd/MM/yyyy"); // para formatar a data
    
    // temos que autenticar o cliente no sistema
    Pessoa cliente = null;
    while(cliente == null){
      System.out.print("\nPrezado(a) cliente, informe seu id ou nome: ");
      String pesquisaPessoa = entrada.nextLine();
      // chamamos o mÃ©todo que pesquisa a pessoa
      cliente = pesquisarPessoa(pesquisaPessoa);
      if(cliente == null){ // pessoa nÃ£o encotrado
        System.out.print("\nCliente nÃ£o encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
        int opcaoTemp = Integer.parseInt(entrada.nextLine());
        if(opcaoTemp == 2){
          return 1; // saÃ­mos daqui e voltamos para o menu anterior  
        }
      }
    }
    
    while(true){ // exibe continuamente o menu de opÃ§Ãµes
      limparTela(); // tire se nÃ£o limpar a tela no seu sistema operacional
      System.out.println("\n:: A C E S S O   D O   C L I E N T E ::\n");
      System.out.println("Cliente Selecionado: " + cliente.getNome() + "\n");
      System.out.println("Escolha a opÃ§Ã£o desejada");
      System.out.println("1 - Listar Minhas Contas BancÃ¡rias");
      System.out.println("2 - Efetuar DepÃ³sito");
      System.out.println("3 - Efetuar Saque");
      System.out.println("4 - Efetuar TransferÃªncia");
      System.out.println("5 - Obter Extrato");
      System.out.println("6 - Voltar Menu Anterior");
      System.out.print("Sua opÃ§Ã£o: ");
      int opcao = Integer.parseInt(entrada.nextLine()); // lÃª a opÃ§Ã£o do usuÃ¡rio
    
      switch(opcao){
        case 1: // vamos listar as contas bancÃ¡rias relacionaas a esta cliente
          // percorremos o ArrayLista de contas do cliente atual
          for(int i = 0; i < cliente.getContas(bancos).size(); i++){
            Conta contaAtualTemp = cliente.getContas(bancos).get(i); // conta da iteraÃ§Ã£o atual
            System.out.println("\nId: " + contaAtualTemp.getId());
            System.out.println("NÃºmero da conta: " + contaAtualTemp.getNumero());
            System.out.println("Cliente: " + contaAtualTemp.getCliente().getNome());
            System.out.println("AgÃªncia: " + contaAtualTemp.getAgencia().getNumero() + " - " 
              + contaAtualTemp.getAgencia().getCidade());
            System.out.println("Banco: " + contaAtualTemp.getAgencia().getBanco().getNumero() + " - " 
              + contaAtualTemp.getAgencia().getBanco().getNome());
            System.out.println("Saldo atual: " + contaAtualTemp.getSaldo());
            System.out.println("Limite atual: " + contaAtualTemp.getLimite());
          }
            
          break;
        case 2: // vamos efetuar um novo depÃ³sito em uma das contas
          System.out.print("\nInforme o Id da Conta: ");
          idConta = Integer.parseInt(entrada.nextLine());
          System.out.print("Valor do DepÃ³sito: ");
          double valorDeposito = Double.parseDouble(entrada.nextLine());
          
          // primeiro temos que encontrar essa conta
          conta = pesquisarContaPorId(idConta);
          if(conta == null){ // conta nÃ£o enconrada
            System.out.print("\nConta nÃ£o encontrada. NÃ£o foi possÃ­vel efetuar o depÃ³sito.\n");
          }
          else{
            conta.setSaldo(conta.getSaldo() + valorDeposito);
            // vamos gerar uma transaÃ§Ã£o para o extrato
            Transacao.contadorTransacoes++;
            transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(), 
              "DEPOSITO", valorDeposito, 'C');
            conta.getTransacoes().add(transacao);
            System.out.print("\nDepÃ³sito efetuado com sucesso.\n");
          }
          
          break;
        
        case 3: // vamos efetuar um novo saque em uma das contas
          System.out.print("\nInforme o Id da Conta: ");
          idConta = Integer.parseInt(entrada.nextLine());
          System.out.print("Valor do Saque: ");
          double valorSaque = Double.parseDouble(entrada.nextLine());
          
          // primeiro temos que encontrar essa conta
          conta = pesquisarContaPorId(idConta);
          if(conta == null){ // conta nÃ£o enconrada
            System.out.print("\nConta nÃ£o encontrada. NÃ£o foi possÃ­vel efetuar o saque.\n");
          }
          else{
            // a conta possui saldo ou limite suficiente?
            if(valorSaque > (conta.getSaldo() + conta.getLimite())){
              System.out.print("\nSaldo e limite insuficientes para este saque.\n");  
            }
            else{  
              conta.setSaldo(conta.getSaldo() - valorSaque);
              // vamos gerar uma transaÃ§Ã£o para o extrato
              Transacao.contadorTransacoes++;
              transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(), 
                "SAQUE", valorSaque, 'D');
              conta.getTransacoes().add(transacao);
              System.out.print("\nSaque efetuado com sucesso.\n");
            }
          }
          
          break;  
          
        case 4: // vamos efetuar uma nova transferÃªncia entre contas
          System.out.print("\nInforme o Id da Conta do DÃ©bito: ");
          idConta = Integer.parseInt(entrada.nextLine());
          System.out.print("Informe o Id da Conta do CrÃ©dito: ");
          int idContaCredito = Integer.parseInt(entrada.nextLine());
          System.out.print("Valor da TransferÃªncia: ");
          double valorTransferencia = Double.parseDouble(entrada.nextLine());
          
          // primeiro temos que encontrar a conta do dÃ©bito, de onde sairÃ¡ o dinheiro
          conta = pesquisarContaPorId(idConta);
          // agora temos que encontrar a conta do crÃ©dito, para onde irÃ¡ o dinheiro
          Conta contaCredito = pesquisarContaPorId(idContaCredito);
          
          if(conta == null){ // conta de dÃ©bito nÃ£o enconrada
            System.out.print("\nConta de dÃ©bito nÃ£o foi encontrada. NÃ£o foi possÃ­vel efetuar a transferÃªncia.\n");
          }
          else if(contaCredito == null){ // conta de crÃ©dito nÃ£o enconrada
            System.out.print("\nConta de crÃ©dito nÃ£o foi encontrada. NÃ£o foi possÃ­vel efetuar a transferÃªncia.\n");
          }
          else if(contaCredito.getId() == conta.getId()){ // transferÃªncia para a mesma conta?
            System.out.print("\nAs contas de dÃ©bito e crÃ©dito nÃ£o podem ser a mesma.\n");
          }
          else{
            // a conta de dÃ©bito possui saldo ou limite suficiente?
            if(valorTransferencia > (conta.getSaldo() + conta.getLimite())){
              System.out.print("\nSaldo e limite insuficientes para esta transferÃªncia.\n");  
            }
            else{  
              // tiramos dinheiro da conta do dÃ©bito
              conta.setSaldo(conta.getSaldo() - valorTransferencia);
              // e adicionamos na conta de crÃ©dito
              contaCredito.setSaldo(contaCredito.getSaldo() + valorTransferencia);
              
              // vamos gerar uma transaÃ§Ã£o para o extrato - dÃ©bito transferÃªncia
              Transacao.contadorTransacoes++;
              transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(), 
                "TRANSFERENCIA", valorTransferencia, 'D');
              conta.getTransacoes().add(transacao);
              
              // vamos gerar uma transaÃ§Ã£o para o extrato - crÃ©dito transferÃªncia
              Transacao.contadorTransacoes++;
              transacao = new Transacao(contaCredito, Transacao.contadorTransacoes, new Date(), 
                "TRANSFERENCIA", valorTransferencia, 'C');
              contaCredito.getTransacoes().add(transacao);
              
              System.out.print("\nTransferÃªncia efetuada com sucesso.\n");
            }
          }
          
          break;
         
        case 5: // vamos mostrar o extrato para a conta selecionada
          System.out.print("\nInforme o Id da Conta: ");
          idConta = Integer.parseInt(entrada.nextLine());
          
          // primeiro temos que encontrar essa conta
          conta = pesquisarContaPorId(idConta);
          if(conta == null){ // conta nÃ£o enconrada
            System.out.print("\nConta nÃ£o encontrada. NÃ£o foi possÃ­vel efetuar o depÃ³sito.\n");
          }
          else{
            System.out.println("");
            System.out.println("        E X T R A T O   B A N C Ã� R I O         ");
            System.out.println("------------------------------------------------");
            System.out.println("Cliente: " + conta.getCliente().getNome());
            System.out.println("Banco: " + conta.getAgencia().getBanco().getNome());
            System.out.println("AgÃªncia: " + conta.getAgencia().getNumero() + " - " + conta.getAgencia().getCidade());
            System.out.println("Conta: " + conta.getNumero());
            System.out.println("------------------------------------------------");
            System.out.println("DATA        HISTORICO           DOC        VALOR");
            System.out.println("------------------------------------------------");
 
            for(int i = 0; i < conta.getTransacoes().size(); i++){
              Transacao t = conta.getTransacoes().get(i); // representa a transaÃ§Ã£o da iteraÃ§Ã£o atual
              
              String valorFormatado = nf.format(t.getValor());
              System.out.println(formato.format(t.getData()) + "  " + String.format("%-20s", t.getHistorico()) + 
                String.format("%1$5s", t.getId()).replace(' ', '0') +  
                String.format("%10s", valorFormatado.replace("R$ ", "")) + t.getLetra());
            }
            
            System.out.println("------------------------------------------------");
            String valorFormatado = nf.format(conta.getSaldo());
            System.out.println("SALDO ATUAL:" + String.format("%36s", valorFormatado.replace("R$ ", "")));
            valorFormatado = nf.format(conta.getLimite());
            System.out.println("LIMITE:" + String.format("%41s", valorFormatado.replace("R$ ", "")));
            valorFormatado = nf.format(conta.getSaldo() + conta.getLimite());
            System.out.println("SALDO + LIMITE:" + String.format("%33s", valorFormatado.replace("R$ ", "")));
            System.out.println("------------------------------------------------");
          }
          
          break;  
          
        case 6:
          return 1; // volta para o menu principal
      }  
    }
  }
  
  // salva todos os dados do sistema em um arquivo texto
  public void salvarDadosArquivo(){
    // tenta salvar o objeto Sistema em um arquivo
    try {
      FileOutputStream arquivoSaida = new FileOutputStream("C:\\estudos_java\\sistema_bancario_oop_java_console_2\\dados.dat");
      ObjectOutputStream objetoSaida = new ObjectOutputStream(arquivoSaida);
      objetoSaida.writeObject(this);
      objetoSaida.close();
      //System.out.println("\nOs dados foram salvos com sucesso.\n");
    } 
    catch (Exception ex) {
      System.out.println("\nNÃ£o foi possÃ­vel gravar os dados em arquivo. Erro: " + ex.getMessage() + "\n");
    }
  }
  
  // carrega todos os dados do sistema a partir de um arquivo texto
  public static Sistema carregarDadosArquivo(){
    Sistema sistema = null;
    // tenta ler o objeto Sistema a partir de um arquivo
    try {
      FileInputStream fi = new FileInputStream(new File("C:\\estudos_java\\sistema_bancario_oop_java_console_2\\dados.dat"));
      ObjectInputStream oi = new ObjectInputStream(fi);
      sistema = (Sistema) oi.readObject();
    } 
    catch (Exception ex) {
      System.out.println("\nNÃ£o foi possÃ­vel ler os dados a partir do arquivo. Erro: " + ex.getMessage() + "\n");
    }
    
    return sistema;
  }
  
  // mÃ©todo que limpa a tela. Funciona rodando fora da IDE. Testado no Windows 10
  public void limparTela(){
    // basta retirar os comentÃ¡rios
    /*
    try{
      String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
      if(operatingSystem.contains("Windows")){        
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
        Process startProcess = pb.inheritIO().start();
        startProcess.waitFor();
      } else {
        ProcessBuilder pb = new ProcessBuilder("clear");
        Process startProcess = pb.inheritIO().start();

        startProcess.waitFor();
      } 
    }catch(Exception e){
      System.out.println(e);
    }
    */
  }
}
