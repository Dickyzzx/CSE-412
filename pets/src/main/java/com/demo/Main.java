package com.demo;

import com.demo.dao.CustomerOrderDao;
import com.demo.dao.PetsDao;
import com.demo.dao.ProductDao;
import com.demo.dao.SellerSuppDao;
import com.demo.dao.UserDao;
import com.demo.dao.Impl.CustomerOrderDaoImpl;
import com.demo.dao.Impl.PetsDaoIml;
import com.demo.dao.Impl.ProductDaoImpl;
import com.demo.dao.Impl.SellerSuppDaoImpl;
import com.demo.dao.Impl.UserDaoImpl;

import com.demo.entity.Pets;
import com.demo.entity.Product;
import com.demo.entity.SellerSupp;
import com.demo.entity.User;
import com.demo.entity.dto.UserMap;
import com.demo.service.*;
import com.demo.service.impl.*;
import com.demo.util.InitTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;

public class Main {
	
	
	
	
	
    private static UserDao userDao = new UserDaoImpl();
    private static PetsDao petsDao = new PetsDaoIml();
    private static SellerSuppDao sellerSuppDao = new SellerSuppDaoImpl();
    private static ProductDao productDao = new ProductDaoImpl();
    private static CustomerOrderDao orderDao = new CustomerOrderDaoImpl();






    public static void main(String[] args) {
    	
    	//selection for users
    	//select all user ,if params is null select all,if params input select user with search text
         List<User> users = new UserDaoImpl().selectAll(null);
         users.forEach(System.out::println);
         
    	
    	 // selection for pets
         //select all pets ,if params is null select all,if params input userid ,select user publish pets
    	//List<Pets> pets = petsDao.selectAll(null);
    	// pets.forEach(System.out::println);
    	
    	//user register 
    	//Main main  = new Main();
    	//main.register(); 
         
         //注册宠物
      // Main main = new Main();
       // main.addPets();
         
         //selection for sellec_supps
       // List<SellerSupp> sellers = sellerSuppDao.selectAll();
      // sellers.forEach(System.out::println);
    	   
    	   
    	  //delete pets
    	 // Main main = new Main();
    	 // main.deletePets("0001");
        
        //delete one pets 
        //you must me input param  id is pets id uid is usee uid
        //petsDao.deleteById("ce8c4490a58a47858a86ecd5f0d1707b", "123123");
    	
    	//init table ,trigger must execute trigge.sql in  pgadmin4
    	//zhixing fangfa zhiqian must be initTable.txt zhiwei  1a
    	
    	
    	//create table
    	/*
    	Main main = new Main();
    	try {
			main.InitTable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	
    	
    	   
    }

    /**
     * First of all, you need to create your own database, and then modify jdbc:postgresql://127.0.0.1:5432/pets in druid.properties to be your own database.
     * initialize the table trigger, you need to manually execute the contents of trigger.sql in the pgadmin4 client
     */
    public void InitTable() throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        Properties pros = new Properties();
        pros.load(is);
        String initTablePath = pros.getProperty("configpath");
        BufferedReader b = new BufferedReader(new FileReader
(initTablePath));
        String s = null;
       StringBuilder config = new StringBuilder();
       while ((s = b.readLine()) != null) {
    	   config.append(s);
       }
       
       	b.close();
    if (config.toString().split("=")[1].equals("1")) {
        new InitTable();
        String newConfig = "initTable=2";
        RandomAccessFile rm = new RandomAccessFile(initTablePath, "rw");
        rm.writeBytes(newConfig);
        rm.close();

    }
    }

    /**
     * login
     * @param uid
     * @param password
     */
    public void login(String uid, String password) {
        User user1 = userDao.selectUserByUidAndPassword(uid, password);
        UserMap.setName(user1.getName());
        UserMap.setPassword(user1.getPassword());
        UserMap.setUid(user1.getUid());
        System.out.println("The logged in user is" + user1);
    }

    /**
     * add user
     *
     * @return
     */
    public void register() {
        //Register two users one 0001 and one 0002
        
            User user = new User();
            user.setUid("1");
            user.setName("A");
            user.setPhone("123123123");
            //This step is actually not necessary, the default password is 123456
            user.setPassword("123456");
            user.setZipCode("123456");
            user.setEmail("A@google.com");
            if (userDao.insertOne(user)) {
                System.out.println("Registered users are" + user);
            } else {
                System.out.println("user is exist" + user);
            }
        
    }

    /**
     * add pets
     */
    public void addPets() {
      
            Pets pets = new Pets();
            pets.setName("T1");
            pets.setAge(20);
            pets.setPrice(new BigDecimal(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP));
            pets.setLocation("United States");
            pets.setBreed("UK short");
            pets.setSpecies("UK short");
            pets.setSellerUid("123123");
            System.out.println("Pets posted as：" + pets);
            petsDao.insertOne(pets);
        
    }


    /**
     * add product
     */
    public void addProduct() {
        
            Product product = new Product();
            product.setBrand("ISLE OF DOGS ");
            product.setPrice(new BigDecimal(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP));
            product.setDescription("this is a product");
            product.setSellerUid("123456");
            System.out.println("The posted items are：" + product);
            productDao.insertOne(product);
        
    }

    /**
     * Buy the first pet of all pets
     */
    public Pets buyPets(String uid) {
        List<Pets> pets = petsDao.selectAll(null);
        Pets pets1 = null;
        if (pets.isEmpty()) {
            System.out.println("the pets table is not have record");
        } else {
            if (pets.get(0).getSellerUid().equals(UserMap.getUid())) {
                System.out.println("You cannot buy your own published pet");
            } else {
                pets1 = pets.get(0);
                petsDao.buyPets(pets1.getId(), uid);
            }
        }
        return pets1;
    }

    /**
     * buyProduct
     * Buy the first product of all products
     *
     * @return
     */
    public Product buyProduct(String uid) {
        List<Product> products = productDao.selectAll(null);
        Product product = null;
        if (products.isEmpty()) {
            System.out.println("the product table is not have record");
        } else {
            if (products.get(0).getSellerUid().equals(UserMap.getUid())) {
                System.out.println("you cannot buy your own published product");
            } else {
                product = products.get(0);
                //if you buy one pets exceute productDao.butProduct("pid,"uid") 
                //in main example delete one pets
                productDao.buyProduct(product.getId(), uid);
            }
        }
        return product;
    }

    public void deletePets(String uid) {
        List<Pets> pets = petsDao.selectAll(null);
        if (pets.isEmpty()) {
            System.out.println("the pets table is not have record");
        } else {
            for (Pets p : pets) {
                if (p.getSellerUid().equals(uid)) {
                    if (p.getStatus().equals("2")) {
                        System.out.println("This pet cannot be deleted because the pet has already been purchased");
                    } else {
                        System.out.println("The deleted pets are" + p);
                        petsDao.deleteById(p.getId(), uid);
                    }
                } else {
                    System.out.println("you don't delete this record" + p + "because the record is not you publish");
                }
            }
        }
    }

    public void deleteProduct(String uid) {
        List<Product> products = productDao.selectAll(null);
        if (products.isEmpty()) {
            System.out.println("the product table is not have record");
        } else {
            for (Product p : products) {
                if (p.getSellerUid().equals(uid)) {
                    if (p.getStatus().equals("2")) {
                        System.out.println("This pet cannot be deleted because the product has already been purchased");
                    } else {
                        System.out.println("The deleted pet supplies are" + p);
                        productDao.deleteById(p.getId(), uid);
                    }
                } else {
                    System.out.println("you don't delete this record" + p + "because the record is not you publish");
                }
            }
        }
    }
}
