drop schema if exists proyecto cascade;
create schema proyecto;
create domain dniPos AS INTEGER  default 2 check (value>1) NOT NULL;
create domain tiposCarnet AS varchar (10) check (value ='B2'or value='B1' or value='C1');

--
-- Tabla para Personas
--

DROP TABLE IF EXISTS  proyecto.Personas cascade;
create table proyecto.Personas (
	dni dniPos ,
	nombre varchar(25),
	apellido varchar (25),
	direccion varchar (50),
	constraint dniPos check (dni>0),
	constraint clavePRimaria primary key (dni)
);

--
--Carga de datos a Personas
--

insert into proyecto.Personas values
(10,'Francisco','Pedernera','San Lorenzo 21'),
(15,'Ricardo','Trimboli','Santiago del Estero 23'),
(58,'Elio','Pedernera','Velez 421'),
(96,'Leopoldo','Buri','Muñiz 23'),
(63,'ignacio','Marinelli','Buenos Aires 782'),
(89,'Manuel','Bringas','Bolivia 781'),
(98,'Romina','Lante','Pedernera 5866'),
(55,'Maximo','Esternon','Patagonia 8962'),
(219, 'Juan',' Pérez', 'Calle Principal 123'),
(385, 'María',' García', 'Calle Secundaria 456'),
(572, 'Pedro',' Rodríguez', 'Avenida Central 789'),
(640, 'Ana',' López', 'Calle Nueva 321'),
(813, 'Luis',' Martínez', 'Calle Mayor 654'),
(957, 'Laura',' Sánchez', 'Avenida del Sol 987'),
(426, 'Carlos',' Fernández', 'Calle del Parque 654'),
(193, 'Sofía',' Torres', 'Calle de la Luna 321'),
(504, 'Andrés',' Ramírez', 'Avenida de los Árboles 789'),
(871, 'Isabella',' Vargas', 'Calle Principal 123'),
(305, 'Gabriel',' Mendoza', 'Calle Secundaria 456'),
(652, 'Valentina',' Castro', 'Avenida Central 789'),
(724, 'Daniel',' Ríos', 'Calle Nueva 321'),
(891, 'Carolina',' Herrera', 'Calle Mayor 654'),
(437, 'Martín',' Silva', 'Avenida del Sol 987');

--
--Tabla para Empleados
--

DROP TABLE IF EXISTS  proyecto.Empleados cascade;
create table proyecto.Empleados (
    dni integer NOT NULL,
    constraint claveForaneaDNIEmpleados foreign key (dni) references proyecto.Personas,
    constraint clavePrimariaEmpleados primary key (dni)
);

--
--Carga de datos Empleados
--

insert into proyecto.Empleados values (10),(15),(58),(96),(63),(89),(219);

--
--Tabla de Secretaria
--

DROP TABLE IF EXISTS  proyecto.Secretaria cascade;
create table proyecto.Secretaria (
	dni dniPos,
	constraint clavePrimariaSecretaria primary key (dni)  

);

--
--Carga da datos Secretaria
--

insert into proyecto.Secretaria values (63),(89),(219);

--
--Tabla de Clientes
--

DROP TABLE IF EXISTS  proyecto.Clientes cascade;
create table proyecto.Clientes (
	dni dniPos,
	constraint claveForaneaDNIClientes foreign key (dni) references proyecto.Personas,
	constraint clavePrimariaClientes primary key (dni)
);

--
--Carga de datos Cliente
--

insert into proyecto.Clientes values 
(98),(55),(385),(572),(640),(813),(957),(426),(193),(504),(871),(305),(652),(724),(891),(437);

--
--Tabla de Mecanicos
--

DROP TABLE IF EXISTS  proyecto.Mecanicos cascade;
create table proyecto.Mecanicos (
	dni dniPos,
	constraint clavePrimariaMEcanicos primary key (dni)	
);
--
--Carga de Mecanicos
--

insert into proyecto.mecanicos values (10),(15);

--
--Tabla de Telefonos de mecanicos 
--

DROP TABLE IF EXISTS  proyecto.TMecanicos cascade;
create table proyecto.TMecanicos(
	dni dniPos, 
	telefonos varchar(80),
	constraint clavePrimariaTMEcanicos primary key (dni,telefonos),
	constraint claveForaneadni foreign key (dni) references proyecto.Mecanicos
);

--
--carga de datos Telefonos de mecanicos
--

insert into proyecto.tmecanicos values (10,'154115705, 153256799'),(15,'15488965, 156229765');

--
--Tabla de Cargo
--

DROP TABLE IF EXISTS  proyecto.Cargo cascade;
create table proyecto.Cargo(
	codigoCargo serial primary key,
	descripcion text	
);

--
--Carga de datos Cargo
--

insert into proyecto.Cargo(descripcion) values
('Encargado de la teoria'),
('Encargado de la practica'),
('ayudantes de segunda');

--
--Tabla para Instructores
--

DROP TABLE IF EXISTS proyecto.Instructores cascade;
create table proyecto.Instructores (
	codigo integer ,
	tipo_carnet tiposCarnet,
	dni dniPos ,
	constraint clavePrimariaInstructores primary key (dni),
	constraint CFcodigo12 foreign key (codigo) references proyecto.cargo (codigoCargo)
);

--
--Carga de datos Instructores
--

insert into proyecto.Instructores(codigo, dni, tipo_carnet) values 
(1,58,'B2'),(3,96,'C1'),(2,10,'B1'),(1,219,'B2');

--
--Tabla para Clases
--

DROP TABLE IF EXISTS  proyecto.Clases cascade;
create table proyecto.Clases (
	codigoClases SERIAL primary key,
	descripcion varchar (40),
	cupo_max integer ,
	dni dniPos , 
	dni_secretaria dniPos,

	constraint CFdni foreign key (dni) references proyecto.instructores,
	constraint CFdniSecretaria foreign key(dni_secretaria) references proyecto.Secretaria,
	constraint ControlCupoMaximo check(cupo_max<50)
);

--
--Carga de datos Clases
--

insert into proyecto.Clases(descripcion,cupo_max,dni,dni_secretaria) values 
('Manejo dunas',49,58,63),
('Manejo de deportivos',10,10,89),
('Manejo de camiones',13,96,219);

--
--Tabla para Dictan
--

DROP TABLE IF EXISTS  proyecto.Dictan cascade;
create table proyecto.Dictan (
	codigo SERIAL,
	dni dniPos,
	constraint CPDictan primary key (codigo,dni),
	constraint CLaveForaneaDictanCodigo foreign key (codigo) references proyecto.Clases ,
	constraint CLaveForaneaDictanDni foreign key (dni) references proyecto.Instructores
);

--
--Carga de datos Dictan
--

insert into proyecto.Dictan values (1,58),(2,10),(3,219);

--
--Tabla para Toman
--

DROP TABLE IF EXISTS  proyecto.Toman cascade;
create table proyecto.Toman (
	codigo integer not null ,
	dni integer not null,
	constraint PrimaryKeyToman primary key (codigo , dni ),
	constraint CFTomanCodigo foreign key (codigo) references proyecto.Clases ,
	constraint CFTomanDni foreign key (dni) references proyecto.Clientes 
);

--
--Carga de datos Toman
--
insert into proyecto.Toman(codigo, dni)values
(1,385),(2,385),(1,572),(1,640),(1,98),(1,55),(2,813),(2,957),(2,426),(2,193),(3,504),(3,871),(3,305),(3,652),(3,724),(1,891),(1,437);

--
--Tabla para Materiales
--

DROP TABLE IF EXISTS  proyecto.Materiales cascade;
create table proyecto.Materiales (
	numero integer , 
	costo float ,
	descripcion varchar (50),
	codigo serial,
	dni dniPos, 
	constraint CPMateriales primary key (numero),
	constraint CFMaterialesDni foreign key (codigo,dni) references proyecto.Dictan(codigo,dni),
	constraint CFMaterialesCodigo foreign key (codigo) references proyecto.Clases,
	constraint CFMaterialesDniInstructores foreign key (dni) references proyecto.Instructores,
	constraint elNumeroDelMaterialPositivo check (numero >0 ),
	constraint costoPositivo check (costo>0)
);

--
--Carga de datos Materiales
--

insert into proyecto.Materiales(numero,costo,descripcion,codigo,dni) values 
(58965,256.32,'Rema',1,58),
(56232,1236.52,'conos de manejo',2,10),
(1234,10000,'tinta impresora',3,219);

--
--Creamos la tabla nueva donde vamos a guardar los datos de la tabla antes de hacer un cambio en la misma 
--

DROP TABLE IF EXISTS  proyecto.cambiosCostosMateriales cascade;
create table proyecto.cambiosCostosMateriales (
	numero integer ,
	fechaCambio DATE,
	costoViejo float,
	costoNew float,
	autor varchar (20)

);
CREATE OR REPLACE FUNCTION proyecto.auditoria_costos()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.costo <> OLD.costo THEN
        INSERT INTO cambiosCostosMateriales (numero,fechaCambio,costoViejo,costoNew,autor)
        VALUES (old.numero,NOW(),old.costo,new,costo,dni);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';


--Lo que hace esta funcion junto con el tigger es hacer un conteo de cuantos registros (cantidad de perosnas que hay en una clase) para que luego
--cunado hagamos el instert en un alumno en una clase verifique si se puede insertar para que no superer el cupo maximo de esa clase 
CREATE OR REPLACE FUNCTION proyecto.verificar_cupoMAx() RETURNS TRIGGER AS $$
DECLARE
  cantidad_alumnos INTEGER;
BEGIN
  SELECT COUNT(*) INTO proyecto.Toman
  FROM Toman
  WHERE codigo = NEW.codigo;

  IF cantidad_alumnos > (
    SELECT cupo_max
    FROM clases
    WHERE codigo = NEW.codigo
  ) THEN
    RAISE EXCEPTION 'El cupo máximo del curso ha sido superado';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER control_cupoMAx
BEFORE INSERT ON proyecto.Toman
FOR EACH ROW
EXECUTE FUNCTION proyecto.verificar_cupoMax();
