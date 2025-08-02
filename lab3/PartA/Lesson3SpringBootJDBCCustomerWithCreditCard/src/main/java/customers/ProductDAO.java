package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from supplier",namedParameters);
        jdbcTemplate.update("DELETE from product",namedParameters);
    }

    public void save(Product product) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES (:productNumber, :name, :price)", namedParameters);

        Map<String, Object> supplierParams = new HashMap<>();
        Supplier s = product.getSupplier();
        System.out.println(s);
        supplierParams.put("name", product.getSupplier().getName());
        supplierParams.put("phone", product.getSupplier().getPhone());
        supplierParams.put("productNumber", product.getProductNumber());
        jdbcTemplate.update("INSERT INTO supplier(name, phone, productNumber) VALUES(:name, :phone, :productNumber)", supplierParams);
    }

    public Product findByProductNumber(int productNumber) {
        String sql = "SELECT * FROM product WHERE productNumber = :productNumber";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject(sql, namedParameters,
                (rs, rowNum) -> new Product(
                        rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        Supplier supplier = getSupplierForProduct(productNumber);
        product.setSupplier(supplier);

        return product;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        List<Product> products = jdbcTemplate.query(sql,
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product(
                        rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        for (Product product : products) {
            product.setSupplier(getSupplierForProduct(product.getProductNumber()));
        }
        return products;
    }

    public List<Product> findByProductName(String name) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name", name);
        String sql = "SELECT * FROM product WHERE name =:name";

        List<Product> products = jdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) -> new Product(
                        rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        for (Product product : products) {
            product.setSupplier(getSupplierForProduct(product.getProductNumber()));
        }

        return products;
    }

    public void removeProduct(int productNumber) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", productNumber);
        jdbcTemplate.update("DELETE FROM supplier WHERE productNumber = :productNumber", namedParameters);
        jdbcTemplate.update("DELETE FROM product WHERE productNumber = :productNumber", namedParameters);
    }

    private Supplier getSupplierForProduct(int productNumber) {
        String sql = "SELECT * FROM supplier WHERE productNumber = :productNumber";
        Map<String, Object> params = Map.of("productNumber", productNumber);

        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            Supplier supplier = new Supplier();
            supplier.setName(rs.getString("name"));
            supplier.setPhone(rs.getString("phone"));
            return supplier;
        });
    }
}
