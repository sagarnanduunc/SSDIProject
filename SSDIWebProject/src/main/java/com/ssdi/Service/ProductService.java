package com.ssdi.Service;

import com.ssdi.Dao.IProductDao;
import com.ssdi.Dao.ProductDao;
import com.ssdi.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by prayas on 3/20/2017.
 */

@Service
public class ProductService {

    @Autowired
    @Qualifier("product")
    private ProductDao productDao;

    public Collection<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

}