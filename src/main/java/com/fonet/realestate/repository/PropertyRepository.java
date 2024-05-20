package com.fonet.realestate.repository;

import com.fonet.realestate.model.Property;
import com.fonet.realestate.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
    List<Property> findByOwnerIdOrderByIdDesc(Long customerId);

    @Query("SELECT p FROM Property p WHERE " +
            "((p.title LIKE %:kelime% OR :kelime IS NULL) OR " +
            "(p.description LIKE %:kelime% OR :kelime IS NULL) ) AND " +
            "(p.address LIKE %:address% OR :address IS NULL)  AND " +
            "(p.roomCount = :roomCount OR :roomCount IS NULL) AND " +
            "(p.squareMeters BETWEEN :squareMeterLowest AND :squareMeterHighest OR (:squareMeterLowest IS NULL AND :squareMeterHighest IS NULL)) AND " +
            "(p.floor BETWEEN :floorLowest AND :floorHighest OR (:floorLowest IS NULL AND :floorHighest IS NULL)) AND " +
            "(p.propertyType = :propertyType OR :propertyType IS NULL) order by p.id desc" )
    List<Property> searchProperties(
                                    @Param("kelime") String kelime,
                                    @Param("address") String address,
                                    @Param("roomCount") Integer roomCount,
                                    @Param("squareMeterLowest") Integer squareMeterLowest,
                                    @Param("squareMeterHighest") Integer squareMeterHighest,
                                    @Param("floorLowest") Integer floorLowest,
                                    @Param("floorHighest") Integer floorHighest,
                                    @Param("propertyType") PropertyType propertyType);
}