import java.net.http.HttpResponse;
import java.nio.file.DirectoryIteratorException;
import java.security.Principal;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Conexion {

        public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean salir = false;
            int opcion; 
            while (!salir) {
                System.out.println("1. Insertar un Cliente ");
                System.out.println("2. Listar todos los clientes y las clases que han realizado :");
                System.out.println("3. Agregar una clase nueva :");
                System.out.println("4. Salir");
                System.out.println("Escribe una de las opciones:");
                opcion = scanner.nextInt();
                switch (opcion) {
                case 1:
                    //Error al cargar el controlador
                    System.out.println("\n");
                    System.out.println("Ingrese los siguientes datos de la perosna que desa registrar :");
                    System.out.println("\n");
                    System.out.println("Ingrese el DNI:");
                    int dni = scanner.nextInt();
                    
                    System.out.println("Ingrese el nombre:");
                    String name = scanner.next();
                    
                    System.out.println("Ingrese el apellido");
                    String apellido = scanner.next();
                    
                    System.out.println("Ingrese la dirreccion y su altura (en el orden pedido):");
                    String calle = scanner.next();
                    int altura =scanner.nextInt();

                    String direccion = calle +" " + altura;
                    System.out.println("El codigo de la calse que va a ir:");
                    int codigo_clas = scanner.nextInt();

                    instertarCliente(dni, name, apellido, direccion, codigo_clas);     
                    break;
                
                    case 2:
                    
                    System.out.print("Estos son todos los clietnes que han realizado alguna clase :");
                    System.out.print("\n");
                    clientes_clases();
                    
                    case 3:
                    System.out.println("Ingrese la decripcion o nombre de la calse que desea registrar:");
                    String descr = scanner.next();

                    System.out.println("El cupo maximo de alumnos que va tener:");
                    int cup_max = scanner.nextInt();

                    System.out.println("El DNI del isntructor que va a estar a cargo de la calse :");
                    int dni_ins = scanner.nextInt();

                    System.out.println("El DNI de la secretaria a cargo de la calse:");
                    int dni_sec = scanner.nextInt();

                    insertarClase(descr , cup_max ,dni_ins , dni_sec);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
                }
            }
        }
        System.out.println("Fin del menú");
        }


        
    
    
    public static  void instertarCliente(int dni_persona ,String nombre , String Apellido , String Direccion, int codigo_clase){
        try{ 
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/Proyecto";
            String username = "postgres";
            String password = "root";
            
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "insert into proyecto.Personas values (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,dni_persona);
            statement.setString(2,nombre);
            statement.setString(3 , Apellido);
            statement.setString(4 , Direccion);

            String query1 = "insert into proyecto.Clientes values (?)";
            PreparedStatement statement2 = connection.prepareStatement(query1);
            statement2.setInt(1,dni_persona);

            String query2 = "insert into proyecto.Toman values (?,?)";
            PreparedStatement statement3 = connection.prepareStatement(query2);
            statement3.setInt(1,codigo_clase);
            statement3.setInt(2,dni_persona);

            try {
                statement.executeUpdate();
                statement2.executeUpdate();
                statement3.executeUpdate();
                System.out.println("Datos insertados correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static ResultSet clientes_clases (){
        try{  
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/Proyecto";
            String username = "postgres";
            String password = "root";
            
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "select * from proyecto.Personas 	natural join(select dni from proyecto.clientes natural join proyecto.toman)as x";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultado = statement.executeQuery();
            int i = 1;
            while (resultado.next()){
                System.out.print(i + ")");
                System.out.print("\n");
                System.out.print(" * DNI:" + resultado.getInt(1));
                System.out.print(" * Nombre:" + resultado.getString(2));
                System.out.print(" * Apellido:" + resultado.getString(3));
                System.out.print(" * Direccion:" + resultado.getString(4));
                System.out.print("\n   ");
                System.out.print("\n   ");
                i++;
            }
            
            connection.close();
            return null;
            //Este catch retorna null si no hay datos registrados en la base de datos
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
    }


    public static void  insertarClase(String descrip , int cupo_max , int dni_instructor , int dni_secretaria ){
        
        try{  
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/Proyecto";
        String username = "postgres";
        String password = "root";
        
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Conexión realizada con éxito");

        String query = "insert into proyecto.clases (descripcion, cupo_max , dni , dni_secretaria) values (?,?,?,?);";

        PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1,descrip);
        statement.setInt(2,cupo_max);
        statement.setInt(3 , dni_instructor);
        statement.setInt(4 , dni_secretaria);
        
        statement.executeUpdate();
        connection.close();
        
        try {
            statement.executeUpdate();
            System.out.println("Datos insertados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }catch (ClassNotFoundException e) {
        System.out.println("Error al cargar el controlador");
    } catch (SQLException e) {
        System.out.println("Error de conexión: " + e.getMessage());
    }

    } 
}


