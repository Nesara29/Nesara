package com.project.eventms.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eventms.entity.Features;
import com.project.eventms.entity.Subcategoryfeatures;

public interface  SubcategoryfeaturesRepository  extends JpaRepository<Subcategoryfeatures, Integer> {
	Subcategoryfeatures	findById(int id);

	List<Subcategoryfeatures> findBySubcategory(int id);
	
	default List<Integer> getFeatures(int subcategory)
	{
		List<Subcategoryfeatures> fobjs = this.findBySubcategory(subcategory);
		
		return  fobjs.stream().map(urEntity -> urEntity.getFeature()).collect(Collectors.toList());
	}
 
}
