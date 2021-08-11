/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.dp2021.RestJPA.repository;

import java.util.List;

import org.obrii.dp2021.RestJPA.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author NEVM PC
 */
@RepositoryRestResource(collectionResourceRel="book", path="book")
public interface LabyrynthRepository extends PagingAndSortingRepository<Book,Long>{
//    List<Labyrynth> findByName(@Param("name") String name);
//    List<Labyrynth> findByOwner(@Param("owner") String owner);
        List<Book> findByTitle(String title);
        List<Book> findAll();
}
