 CREATE DATABASE autenticacion;

 CREATE TABLE usuarios(
 username char(20),
 password char(20),
 timestamp datetime
 );
 
 CREATE TABLE autenticaciones(
 username char(20),
 host char(15),
 timestamp datetime
 );
 