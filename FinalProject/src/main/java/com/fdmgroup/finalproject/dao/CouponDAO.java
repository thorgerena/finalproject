package com.fdmgroup.finalproject.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.model.Coupon;
import com.fdmgroup.finalproject.model.User;

public class CouponDAO {

	private EntityManagerFactory emf;
	
	public CouponDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public void addCoupon(Coupon coupon) throws JPAException {
		
		if (coupon == null) {
			throw new JPAException(JPAException.inputErrorNull());
		}

		EntityManager em = emf.createEntityManager();
		
		if (this.getCoupon(coupon.getCouponID()) == null) {
			em.getTransaction().begin();
			em.persist(coupon);
			em.getTransaction().commit();
			em.close();
		} else {
			throw new JPAException(JPAException.inputErrorDuplicateKey());
		}
	}
	
	public Coupon getCoupon(int couponID) {
		
		EntityManager em = emf.createEntityManager();
		Coupon coupon = em.find(Coupon.class, couponID);
		em.close();
		
		return coupon;
	}
	
	public boolean useCoupon(int couponID) throws JPAException {

		EntityManager em = emf.createEntityManager();
		Coupon existingCoupon = em.find(Coupon.class, couponID);
		
		if (existingCoupon == null) {
			throw new JPAException("Coupon doesn't exist in the database");
		}
		
	
		
		if(existingCoupon.getUses()>0) {
			em.getTransaction().begin();
			existingCoupon.setUses(existingCoupon.getUses()-1);
			em.getTransaction().commit();
			em.close();
			return true;
		} else {
			return false;
		}
	}
	
	public void removeCoupon(int couponID) throws JPAException {
		
		EntityManager em = emf.createEntityManager();
		Coupon coupon = em.find(Coupon.class, couponID);
		
		if (coupon == null) {
			throw new JPAException("Coupon doesn't exist in the database");
		}
		
		em.getTransaction().begin();
		em.remove(coupon);
		em.getTransaction().commit();
		em.close();
	}

	public List<Coupon> listCoupons() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT e from Coupon e", Coupon.class);
		
		List<Coupon> coupons = query.getResultList();

		return coupons;
	}
}
