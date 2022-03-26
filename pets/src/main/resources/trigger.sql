
drop trigger if exists insertPetsTrigger on pets;
drop function if exists insertPetsAndSellSuppInsert;


CREATE OR REPLACE FUNCTION insertPetsAndSellSuppInsert() RETURNS TRIGGER AS $insertPets$
   BEGIN
      INSERT INTO seller_supp(uid, pid, identifier,price) VALUES (new.seller_uid, new.id, 1, new.price);
      RETURN NEW;
   END;
$insertPets$ LANGUAGE plpgsql;

CREATE TRIGGER insertPetsTrigger AFTER INSERT ON pets FOR EACH ROW EXECUTE PROCEDURE insertpetsandsellsuppinsert();
drop trigger if exists buyPetsTrigger on pets;
drop function if exists buyPetsAndCustomerOrderInsert;


CREATE OR REPLACE FUNCTION buyPetsAndCustomerOrderInsert() RETURNS TRIGGER AS $buyPets$
   BEGIN
      INSERT INTO customer_order(uid, pid, identifier,price) VALUES (new.buy_uid, new.id, 1, old.price);
      RETURN NEW;
   END;
$buyPets$ LANGUAGE plpgsql;

CREATE TRIGGER buyPetsTrigger AFTER UPDATE ON pets FOR EACH ROW EXECUTE PROCEDURE buyPetsAndCustomerOrderInsert();



drop trigger if exists insertProductsTrigger on product;
drop function if exists insertProductsAndSellSuppInsert;

CREATE OR REPLACE FUNCTION insertProductsAndSellSuppInsert() RETURNS TRIGGER AS $insertProducts$
   BEGIN
      INSERT INTO seller_supp(uid, pid, identifier,price) VALUES (new.seller_uid, new.id, 2, new.price);
      RETURN NEW;
   END;
$insertProducts$ LANGUAGE plpgsql;

CREATE TRIGGER insertProductsTrigger AFTER INSERT ON product FOR EACH ROW EXECUTE PROCEDURE insertProductsAndSellSuppInsert();
drop trigger if exists buyProductTrigger on product;
drop function if exists buyProductAndCustomerOrderInsert;


CREATE OR REPLACE FUNCTION buyProductAndCustomerOrderInsert() RETURNS TRIGGER AS $buyProduct$
   BEGIN
      INSERT INTO customer_order(uid, pid, identifier,price) VALUES (new.buy_uid, new.id, 2, old.price);
      RETURN NEW;
   END;
$buyProduct$ LANGUAGE plpgsql;

CREATE TRIGGER buyProductTrigger AFTER UPDATE ON product FOR EACH ROW EXECUTE PROCEDURE buyProductAndCustomerOrderInsert();



drop trigger if exists deletePetsAndSellerSuppTrigger on pets;
drop function if exists deletePetsAndSellerSupp;

CREATE OR REPLACE FUNCTION deletePetsAndSellerSupp() RETURNS TRIGGER AS $deletePetsAndSellerSupp$
   BEGIN
      delete from seller_supp where pid = old.id and uid = old.seller_uid;
      RETURN OLD;
   END;
$deletePetsAndSellerSupp$ LANGUAGE plpgsql;

CREATE TRIGGER deletePetsAndSellerSuppTrigger AFTER DELETE ON pets FOR EACH ROW EXECUTE PROCEDURE deletePetsAndSellerSupp();

drop trigger if exists deletepProductsAndSellerSuppTrigger on product;
drop function if exists deletepProductsAndSellerSupp;

CREATE OR REPLACE FUNCTION deletepProductsAndSellerSupp() RETURNS TRIGGER AS $deletepProductsAndSellerSupp$
   BEGIN
      delete from seller_supp where pid = old.id and uid = old.seller_uid;
      RETURN OLD;
   END;
$deletepProductsAndSellerSupp$ LANGUAGE plpgsql;

CREATE TRIGGER deletepProductsAndSellerSuppTrigger AFTER DELETE ON product FOR EACH ROW EXECUTE PROCEDURE deletepProductsAndSellerSupp();

