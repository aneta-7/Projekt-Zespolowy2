package spring.service;

import spring.model.OrderModel;
import java.util.List;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImpl {

    @Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void deleteOrder(OrderModel order) {
		sessionFactory.getCurrentSession().delete(order);	
	}
	

	@SuppressWarnings("unchecked")
	public List<OrderModel> getAllOrders() {
		return sessionFactory.getCurrentSession().getNamedQuery("orders.all").list();
	}

	public void updateOrder(OrderModel order) {
		sessionFactory.getCurrentSession().merge(order);
	}

	public void addOrder(OrderModel order) {
		sessionFactory.getCurrentSession().persist(order);
	}

}
