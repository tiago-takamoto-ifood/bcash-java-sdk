# bcash-java-sdk
Bcash SDK Checkout for JAVA

### Utilizando as credenciais
1. Você pode configurar as credenciais da sua loja em um arquivo properties (bcash.properties) disponível no seu classpath com as seguintes propriedades:
```java
bcash.credentials.email = email@loja.com.br
bcash.credentials.consumerKey = abcdfabcdfabcdf99ab99ab99av99ab99ab99ab1
bcash.credentials.token = AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
```

Suas credenciais podem ser obtidas na área logada do Bcash. A consumerKey pode ser encontrada no menu Ferramentas > Gerenciamento de 
APIs e o token de acesso (chave acesso) no menu Ferramentas > Códigos de integração. Caso a consumerKey não esteja disponível na sua
conta entre em contato com comercial@bcash.com.br.

E utilizar os services com o construtor default: 
```java
TransactionService transactionService = new TransactionService();
```

2. Ou pode informar suas credenciais nas classes Service, por exemplo:
```java
String consumerKey = "abcdfabcdfabcdf99ab99ab99av99ab99ab99ab1";
OAuthCredentials credential = new OAuthCredentials(consumerKey);
TransactionService transactionService = new TransactionService(credential);
```

### Utilizando o Sandbox

Para utilizar o ambiente de testes (sandbox) adicione a seguinte propriedade ao seu bcash.properties: 

```java
bcash.env = sandbox # use prod para o ambiente real 
```

Ou cofigure dinamicamente informando o Environment como argumento no método contrutor dos `services`. 
Utilize Environment.PRODUCTION para a ambiente real e Environment.SANDBOX para o ambiente de testes.
Por exemplo:
```java
TransactionService transactionService = new TransactionService(oAuthcredential, basicCredential, Environment.SANDBOX);
``` 

### Criando uma transação de boleto

```java
TransactionRequest transaction = new TransactionRequest();

/* Informando dados da loja */
transaction.setSellerMail("lojamodelo@bcash.com.br");

/* Informando dados dos produtos comprados */
Product product0 = new Product();
product0.setCode("001");
product0.setDescription("Teste de produto");
product0.setValue(new BigDecimal("10.50")); // valor unitário
product0.setAmount(2);
transaction.setProducts(Arrays.asList(product0));

/* Informando o meio de pagamento */
transaction.setPaymentMethod(PaymentMethodEnum.BANK_SLIP); // ou PaymentMethodEnum.fromCode(10);

/* Informando os dados do comprador */
Customer buyer = new Customer();
buyer.setMail("email@comprador.com");
buyer.setName("João da Silva");
buyer.setCpf("31311053050");
buyer.setCellPhone("11999995555"); // Obrigatório no mínimo 1 telefone
transaction.setBuyer(buyer);

/* Informando os dados de entrega */
Address address = new Address();
address.setAddress("Alameda Santos");
address.setNumber("122"); // Use Address.WITHOUT_NUMBER para endereço sem número
address.setCity("São Paulo");
address.setState(StateEnum.SAO_PAULO); // Ou address.setState(StateEnum.fromAbbreviation("SP");
address.setZipCode("01418000");
buyer.setAddress(address);

/* Informando a visualização e aceitação do contrato bcash */
/* Acessível em https://www.bcash.com.br/checkout/pay/contrato */
transaction.setAcceptedContract(true);
transaction.setViewedContract(true);

TransactionResponse response = null;
try {
    /* Cria cliente para consumir serviço de criação de transações */
	TransactionService service = new TransactionService();
	response = service.create(transaction);
} catch (ServiceException e) {
	/* Tratando erros retornados pela API */
	System.out.println("O serviço retornou um erro:");
	for (ResponseError error : e.getErrors()) {
		System.out.println(error.getCode() + " - " + error.getDescription());
	}
} catch (IOException e) {
	System.out.println("Erro de comunicação:" + e);
}

/* Tratando caso de sucesso */
if (response != null) {
	System.out.println(response.getTransactionId());
	System.out.println(response.getOrderId());
	System.out.println(response.getPaymentLink()); // link para boleto ou tef
	System.out.println(response.getStatus()); // status da transação no bcash
	System.out.println(response.getDescriptionStatus());

	/* Caso haja a recusa nos pagamentos de cartão de crédito o status da transação será cancelado */
	if (TransactionStatusEnum.CANCELLED.equals(response.getStatus())) {
		System.out.println(response.getCancellationCode());
		System.out.println(response.getMessage()); // motivo do cancelamento
	}
}
```

### Comprando com cartão de crédito

Para efetuar compras utilizando cartão de crédito, a bandeira deve ser informada como meio de 
pagamento (TransactionRequest#setPaymentMethod(PaymentMethodEnum)) e os dados do cartão de crédito 
devem ser informados no TransactionRequest#setCreditCard(CreditCardRequest), conforme exemplo abaixo:

```java
/* ... */
transaction.setPaymentMethod(PaymentMethodEnum.VISA); // ou PaymentMethodEnum.fromCode(1);

CreditCardRequest creditCardRequest = new CreditCardRequest();
creditCardRequest.setHolder("JOAO DA SILVA");
creditCardRequest.setNumber("4111111111111111");
creditCardRequest.setMaturityMonth(12);
creditCardRequest.setMaturityYear(2018);
creditCardRequest.setSecurityCode("123");
transaction.setCreditCard(creditCardRequest);
/* ... */
```

### Adicionando comissionamento (Transações dependentes)

Você pode especificar até seis contas para receber comissionamento caso a transação seja aprovada.
Em cada comissionamento deve ser especificado o email da conta que irá receber a comissão e o
valor a ser enviado. Para cada comissionamento uma nova transação de envio de dinheiro será gerada.
A loja pode configurar na área logada do Bcash o valor máximo de comissionamento permitido. Por
padrão a soma das comissões não pode ultrapassar 5% do valor total da transação. 

```java
/* ... */

DependentTransaction dependentTransaction0 = new DependentTransaction();
dependentTransaction0.setEmail("email@comissionado.com");
dependentTransaction0.setValue(new BigDecimal("1.59")); // Utilizar sempre duas casas decimais
transaction.setDependentTransactions(Arrays.asList(dependentTransaction0));
		
/* ... */
```

### Cancelando uma transação
É possível cancelar uma transação informando o identificador da transação Bcash, conforme exemplo abaixo:
```java
// utilizando autenticação do properties ou usar BasicCredentials
TransactionService transactionService = new TransactionService();


TransactionCancelResponse response = null;
try {
	String transactionId = "99999999";
	response = transactionService.cancel(transactionId);
} catch (ServiceException e) {
	System.out.println("O serviço retornou um erro:");
	for (ResponseError error : e.getErrors()) {
		System.out.println(error.getCode() + " - " + error.getDescription());
	}
} catch (IOException e) {
	System.out.println("Erro de comunicação:" + e);
}

if (response != null) {
	System.out.println(response.getTransactionId());
	System.out.println(response.getTransactionStatus());
}
```

### Consultando os dados de uma transação

```java
TransactionService service = new TransactionService();

TransactionSearchResponse response = null;
try {
	String transactionId = "710";  // id da transação Bcash
	response = service.searchById(transactionId); // Ou service.searchByOrderId(orderId); 
} catch (IOException e) {
	System.out.println("Erro de comunicação: " + e.getMessage());
} catch (ServiceException e) {
	System.out.println("O serviço retornou um erro: ");
	for (ResponseError error : e.getErrors()) {
		System.out.println(error.getCode() + " - " + error.getDescription());
	}
}

if (response != null) {
	System.out.println(response.getTransactionId());
	System.out.println(response.getOrderId());
	System.out.println(response.getSellerEmail());

	System.out.println(response.getShippingCost());
	System.out.println(response.getShippingType());

	// valor da soma dos produtos + frete + acrescimo - desconto
	System.out.println(response.getPaymentAmount());
	// valor da soma dos produtos + frete + acrescimo - desconto + taxa de parcelamento
	System.out.println(response.getPaymentAmountWithRate());
	// Valor da transacao - retencao (e menos taxa de parcelamento caso a loja assuma)
	System.out.println(response.getFavoredPaymentAmount());

	System.out.println(response.getBuyer().getName());

	// status da transacao
	System.out.println(response.getStatus());
}
```

### Atualizando o status do pedido

O Bcash realizará as notificações de alteração de statis na URL informada durante a criação da transação:
```java
TransactionRequest transaction = new TransactionRequest();
/* ... */
transaction.setUrlNotification("https://www.minhaloja.com.br/notification");
```

O método `NotificationService#isValid(HttpServletRequest, HttpServletResponse, BigDecimal)` irá confrontar os dados 
recebidos por POST com as informações existentes no Bcash. Caso as informações estejam corretas este método retorna
`true` e o status do seu pedido deverá ser atualizado conforme o enviado no POST (`NotificationService.getStatus(HttpServletRequest)`). 
Caso contrário o status do seu pedido não deve ser alterado.

É importante notificar situações de erro ao Bcash caso algo impeça a atualização do status no seu sistema. Isto fará com que
o Bcash realize uma nova tentativa de notificação posteriormente. Isso deve ser feito retornando um cabeçalho HTTP diferente de 200.
O Bcash realizará até 5 tentativas de notificação para aquele status.

Abaixo um exemplo de como implementar um servlet para receber as notificações de alteração de status utilizando a SDK:
```java
import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bcash.domain.transaction.TransactionStatusEnum;

public class Notification extends HttpServlet {

	private static final long serialVersionUID = -5338769121304568942L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NotificationService notificationService = new NotificationService();
		String transactionId = notificationService.getTransactionId(req);
		// Ou String orderId = notificationService.getOrderId(req);

		/* consultar valor total do pedido no seu sistema */
		/* valor dos produtos + frete + acréscimo - desconto */
		BigDecimal transactionValue = ...;

		try {
			if (notificationService.isValid(req, resp, transactionValue)) {
				// dados válidos
				TransactionStatusEnum status = notificationService.getStatus(req);
				// alterar o status do seu pedido
				// ...
				// Notificar sucesso ao bcash
				notificationService.sendSuccess(resp);
			} else {
				// dados não procedem com informações existentes no Bcash, não alterar o status
			}
		} catch (ServiceException e) {
			// salvar em log os erros retornados em e.getErros();
			resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Ocorreu um erro ao processar a requisição.");
		} catch (Exception e) {
			// salvar em log o erro de comunicação
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado.");
		}
	}
}

```

### Simulando uma notificação
Os ambientes do Bcash realizam as notificações apenas para endereços disponíveis na internet. Se seu ambiente de testes está disponível
para a internet você pode disparar um POST de notificação acessando a interface de detalhes de uma transação na área logada do Bcash (https://sandbox.bcash.com.br).
Caso seu ambiente não esteja disponível na internet você pode utilizar nosso simulador de notificações conforme o exemplo abaixo:
```java
import java.io.IOException;

import br.com.bcash.domain.transaction.TransactionStatusEnum;
import br.com.bcash.test.NotificationSimulator;

public class Main {

	public static void main(String[] args) throws IOException {
		NotificationSimulator.Notification notification = new NotificationSimulator.Notification();
		notification.setUrl("http://localhost/notification"); // informar a url do seu servlet que receberá as notificações
		notification.setTransactionId("710"); // informar a transação Bcash que será atualizada
		notification.setOrderId("710"); // informar o seu identificado do pedido
		notification.setStatus(TransactionStatusEnum.APPROVED); // informar o status a ser simulado

		NotificationSimulator simulator = new NotificationSimulator();
		String response = simulator.test(notification);
		System.out.println(response);
	}

}
```  