# bcash-java-sdk
Bcash SDK Checkout for JAVA

### Utilizando as credenciais
1. Você pode configurar as credenciais da sua loja em um arquivo properties (bcash.properties) disponível no seu classpath com as seguintes propriedades:
```java
bcash.credentials.email = email@loja.com.br
bcash.credentials.consumerKey = abcdfabcdfabcdf99ab99ab99av99ab99ab99ab1
bcash.credentials.token = AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
```

E utilizar os services com o construtor default: 
```java
TransactionService service = new TransactionService();
```

2. Ou pode informar suas credenciais nas classes Service, por exemplo:
```java
String consumerKey = "abcdfabcdfabcdf99ab99ab99av99ab99ab99ab1";
OAuthCredentials credential = new OAuthCredentials(consumerKey);
TransactionRequest transaction = new TransactionRequest(credential);
```

### Criando uma transação de boleto

```java
TransactionRequest transaction = new TransactionRequest();

transaction.setSellerMail("lojamodelo@bcash.com.br");

Product product0 = new Product();
product0.setCode("001");
product0.setDescription("Teste de produto");
product0.setPrice(new BigDecimal("10.50")); // valor unitário
product0.setQuantity(2);
transaction.setProducts(Arrays.asList(product0));

transaction.setPaymentMethod(PaymentMethodEnum.BANK_SLIP);

Customer buyer = new Customer();
buyer.setMail("comprador@comprador.com");
transaction.setBuyer(buyer);

Address address = new Address();
address.setAddress("Alameda Santos");
address.setNumber("122"); // Use Address.WITHOUT_NUMBER para endereço sem número
address.setCity("São Paulo");
address.setState(StateEnum.SAO_PAULO); // Ou address.setState(StateEnum.fromAbbreviation("SP");
address.setZipCode("01418000");
buyer.setAddress(address);

transaction.setAcceptedContract(true);
transaction.setViewedContract(true);

try {
	TransactionService service = new TransactionService();
	service.create(transaction);
} catch (ServiceException e) {
	System.out.println("O serviço retornou um erro:");
	for (ResponseError error : e.getErrors()) {
		System.out.println(error.getCode() + " - " + error.getDescription());
	}
}
```