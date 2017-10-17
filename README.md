Com o terminal aberto no diretório do projeto, execute os comandos abaixo:

docker build -t loja/banco ./postgres

docker build -t loja/web .

docker run -p 5433:5432 -d --name dbloja loja/banco

docker run -p 8080:8080 --name lojaWeb --link dbloja:bancohost loja/web
