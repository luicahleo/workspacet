--
-- Conexión con la BBDD
--
\c notasfast;

--
-- Borrado de datos
--
DELETE FROM notas;
DELETE FROM usuarios;
--
-- Insercion de datos de ejemplo
--
INSERT INTO usuarios VALUES('usuario', 'clave',1);
INSERT INTO usuarios VALUES('usuario2','clave',1);
INSERT INTO usuarios VALUES('usuario3','clave',1);
INSERT INTO usuarios VALUES('admin',   'clave',0);
INSERT INTO usuarios VALUES('admin2',  'clave',0);
INSERT INTO usuarios VALUES('admin3',  'clave',0);
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('usuario','Nota de prueba 1','Esta es la nota 1','../imagenes/nota.png', 'personal', 'blue');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('usuario','Nota de prueba 2','Esta es la nota 2','../imagenes/nota2.png', 'trabajo', 'red');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('usuario','Nota de prueba 3','Esta es la nota 3','../imagenes/nota2.png', 'personal', 'blue');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('usuario','Nota de prueba 4','Esta es la nota 4','../imagenes/nota2.png', 'trabajo', 'red');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('usuario','Nota de prueba 5','Esta es la nota 5','../imagenes/nota2.png', 'personal', 'blue');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('usuario','Nota de prueba 6','Esta es la nota 6','../imagenes/nota2.png', 'trabajo', 'red');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color) VALUES('admin',  'Nota de prueba 6','Esta es la nota 6','../imagenes/nota2.png', 'administracion', 'yellow');

INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color)
VALUES('usuario2','ETSI','Esta es de usuario2', '../imagenes/logoETSI.png', 'ocio', 'fuchsia');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color)
VALUES('usuario3','Universidad','Esta es de usuario3', '../imagenes/logoUS.gif', 'particular', 'azure');

INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color)
VALUES('admin','Otro título','Esta es otra nota', '../imagenes/logoETSI.png', 'dirección', 'chocolate');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color)
VALUES('admin','Otro título adicional','Esta es de admin','../imagenes/logoUS.gif', 'confidencial', 'magenta');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color)
VALUES('admin2','Info importante','Esta nota es informativa','../imagenes/info.png', 'informativo', 'tomato');
INSERT INTO notas (nombre_usuario, titulo, nota, urlimagen, categoria, color)
VALUES('admin3','Alcance nacional','Esta nota es de difusión nacional','../imagenes/sp.png', 'nacional', 'salmon');


