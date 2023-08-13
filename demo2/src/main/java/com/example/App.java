package com.example;

import java.beans.Statement;
import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingDeque;

import javax.management.RuntimeErrorException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.jdbc.Blob;

import org.hibernate.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.net.HttpURLConnection;


public class App {
    public static void main(String[] args) {
        //BlockingDeque<Connection> pool = null;
        //Connection connection = pool.take();
       // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/person?" +
        //"user=root&password=89Monkey89");
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);
        configuration.configure();


        

        
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuilder builder = new StringBuilder();
            
            while ((output = br.readLine()) != null) {
                builder.append(output);

                try(FileWriter writer = new FileWriter("test.json", false)) {
                    writer.append(builder);
                } catch (Exception e) {
               
                }
            }
            conn.disconnect();

            JSONParser parser = new JSONParser();
            //JSONObject json = (JSONObject) jsonParser.parse(builder);
            //System.out.println(builder);
            File file = new File("https://jsonplaceholder.typicode.com/posts");

            try(FileReader reader = new FileReader("test.json")) {
                JSONArray root = (JSONArray) parser.parse(reader);

                try {
                    ///String state = "INSERT INTO table1(userId, id, title, body)" + "VALUES(?, ?, ?, ?)";
                    //PreparedStatement statement = connection.PreparedStatement(state);
                    //Blob blob = connection.createBlob();
                    //OutputStream os = blob.setBinaryStream(1);
                      //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                //"user=root&password=89Monkey89"
                
                    //List<People> peopleList = new ArrayList<>();
                    for(Object data : root) {
                        JSONObject dataFile = (JSONObject) data;

                        Long idPeople = (Long) dataFile.get("userId");
                        Long id_People = (Long) dataFile.get("id");
                        String titlePeople = (String) dataFile.get("title");
                        String bodyPeople = (String) dataFile.get("body");
                        System.out.println(idPeople);
                        System.out.println(id_People);
                        System.out.println(titlePeople);
                        System.out.println(bodyPeople);
                        SessionFactory sessionFactory = configuration.buildSessionFactory();
                        Session session = sessionFactory.openSession();
                        session.beginTransaction();
                        Person person = Person.builder().userId(idPeople).id(id_People).title(titlePeople).body(bodyPeople).build();
                        session.persist(person);
                        session.getTransaction().commit();
                        
                        
                        

                    }
                    } catch (Exception e) {
                    System.out.println(e);
                }
               
                //System.out.println(reader);
            } catch (Exception e) {
                System.out.println(e);
            }



            
            //JSONParser parser = new JSONParser();
            //JSONObject root = (JSONObject) parser.parse(builder);
            //JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            //BufferedReader br = new BufferedReader(new FileReader("https://jsonplaceholder.typicode.com/posts"));
         //   String st = br.readLine();
          //  String jsonFile = "";
           // while (st != null) {
          //      jsonFile += st;
            //    st = br.readLine();
           // }


            //JSONArray all = (JSONArray) jsonObject.get();
         //   System.out.println(jsonFile);


        } catch (Exception e) {
            System.out.println("Плохо");
            System.out.println(e);


            
        }
    }
}