import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class conec {

    public static void main(String[] args) throws ClassNotFoundException {
        
        try {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/Proyecto";
            String username = "postgres";
            String password = "root";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            boolean salir = false;
            int opcion; 
            while (!salir) {
                System.out.println("1. Insertar un Cliente ");
                System.out.println("2. Listar todos los clientes y las clases que han realizado :");
                System.out.println("3. Agregar una clase nueva :");
                System.out.println("4. Salir");
                System.out.println("Escribe una de las opciones:");
                Scanner scanner;
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
                    //Error al cargar el driver
                    System.out.print("Estos son todos lso clientes que han realizado una clase:");
                    clientes_clases();
                    
                    case 3:
                    System.out.println("Ingrese los siguentes datos:");
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
        }     catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
            System.err.println("Error connecting: " + sqle);
        }
    }

    public static  void instertarCliente(int dni_persona ,String nombre , String Apellido , String Direccion, int codigo_clase) throws ClassNotFoundException{
       

        try{ 
            
            Connection connection;
            String query = "insert into proyecto.Personas values (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,dni_persona);
            statement.setString(2,nombre);
            statement.setString(3 , Apellido);
            statement.setString(4 , Direccion);
            statement.executeUpdate();

            String query1 = "insert into proyecto.toman values (?,?)";
            PreparedStatement statement2 = connection.prepareStatement(query1);
            statement2.setInt(1,codigo_clase);
            statement2.setInt(2,dni_persona);
            
            try {
                statement.executeUpdate();
                System.out.println("Datos insertados correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }


}







