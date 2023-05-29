
# API de Hot Dogs

Serviço responsável pela gestão da loja de cachorros-quente gourmet

# Detalhes do projeto e dependêcias

Este serviço é uma API REST desenvolvida em Java, utilizando Spring Boot como biblioteca para construção do servidor de aplicação. É executando em cima de um Apache Tomcat embutido dentro da aplicação. Projeto Maven, todas as dependências sendo tratadas em seu pom.xml.

# Como executar

## Adicione o Lombok no Eclipse
1. Baixe o jar do Lombok  - [Baixar Lombok](https://projectlombok.org/downloads/lombok.jar)
2. Executar o jar do Lombok
	- Execute usando o terminal de sua preferência com o comando `java -jar lombok.jar` 
	- Ou, clique duas vezes no arquivo baixado.
3. Selecione o local onde o Eclipse esta instalado
	- Clique em *Specify location...* e selecione a pasta onde está o Eclipse.
4. Instalar o Lombok no eclipse
	- Com a pasta do Eclipse selecionada, clique em *Install / Update*.
5. Reinicie o Eclipse
6. Adicionar o Lombok como dependência do seu projeto no `pom.xml`.

		<dependency>
		  <groupId>org.projectlombok</groupId>
		  <artifactId>lombok</artifactId>
		  <scope>provided</scope>
		</dependency>
		
7. Rebuild do seu projeto com `CTRL+B`
8. Após o rebuild talvez seja necessário atualizar o projeto
	1. Botão direito em cima da pasta do projeto e selecione *Maven* > *Update Project*

## Após configurar o Lombok
- Na pasta raíz do projeto execute o comando `java -jar war/hot-dog-api.war`. Isso executará o arquivo *.war* já compilado existente na pasta do projeto.
- **Atenção:** Esse arquivo não funcionará se executado dentro de um servidor de aplicação ou contâiner de servlets.
