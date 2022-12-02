package com.proyecto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.HashMap;
// import java.util.Map;

public class ChoferesDTO {

    static final String BD_Conexion = "jdbc:mysql://localhost:3306/";
    static final String Usuario_BD = "root";
    static final String Contrasena_BD = "root";

    public void saveChoferes(String Nombre, String Apellido,int Antiguedad){

        try(Connection con = DriverManager.getConnection(BD_Conexion, Usuario_BD, Contrasena_BD);
        Statement stmt = con.createStatement()){
            String query = "INSERT INTO proyecto_finalDota.choferes (Nombre,Apellido,Antiguedad) VALUES (" + Nombre + ",'" + Apellido + "'," + Antiguedad + "');";
            stmt.executeUpdate(query);
    
            System.out.println("Persistio en base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Choferes> getChoferes(){

        ChoferesMapping choferesMapping = new ChoferesMapping();
        ArrayList<Choferes> choferes = new ArrayList<Choferes>();
    
        try(Connection con = DriverManager.getConnection(BD_Conexion, Usuario_BD, Contrasena_BD);
        Statement stmt = con.createStatement()){
            String query = "select * from proyecto_finalDota.choferes a ;";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                String NombreBD = result.getString("Nombre");
                String ApellidoBD = result.getString("Apellido");
                int AntiguedadBD = result.getInt("Antiguedad");
                choferes.add(choferesMapping.mapChoferes(NombreBD, ApellidoBD, AntiguedadBD));
        
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choferes;
    }
    
}
