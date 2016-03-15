package com.spring.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.dao.ProductDAO;
import com.spring.model.ProductModel;

@Component
@Service(value="productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductModel> getAllProducts() {
		 return getSessionFactory().getCurrentSession().getNamedQuery("products.all").list();
	}

	@Override
	public void addProduct(ProductModel product) {
		 getSessionFactory().getCurrentSession().persist(product);
		
	}

	@Override
	public ProductModel findProduct(ProductModel product) {
		return sessionFactory.getCurrentSession().get(ProductModel.class, product.getId());
		
	}

	@Override
	public void deleteProduct(ProductModel product) {
		getSessionFactory().getCurrentSession().delete(product);	
	}

	@Override
	public void updateProduct(ProductModel product) {
		 getSessionFactory().getCurrentSession().merge(product);
	}

	@Override
	public boolean createProduct(String name, String description, String price, String type, String color, byte[] foto) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || StringUtils.isEmpty(price) || StringUtils.isEmpty(type)|| StringUtils.isEmpty(color)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		}  else {
			ProductModel product = new ProductModel(name, description, price, type, color, foto);
			productDAO.addProduct(product);
			return true;
		}		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductModel> findProductByName(String name) {
				return sessionFactory.getCurrentSession().getNamedQuery("products.getByName").setString("name", name).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductModel> findProductByType(String type) {
		return sessionFactory.getCurrentSession().getNamedQuery("products.getByType").setString("type", type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductModel> findProductByColor(String color) {
		return sessionFactory.getCurrentSession().getNamedQuery("products.getByColor").setString("color", color).list();
	}

	@Override
	public ProductModel findProductById(long productId) {
		return (ProductModel) sessionFactory.getCurrentSession().getNamedQuery("products.byId").setLong("id", productId).uniqueResult();
	}

	@Override
	public ProductModel findProductByTypeRose() {
		String name = "rose"; 
		return (ProductModel) sessionFactory.getCurrentSession().getNamedQuery("products.getByName").setString("name", name).list().get(1);
	}

	@Override
	public ProductModel findProductByTypeTulips() {
		String name = "tulip";
		return (ProductModel) sessionFactory.getCurrentSession().getNamedQuery("products.getByName").setString("name", name).list().get(1);
	
	}	
}

