package app.helice.myauth.repository;

import org.springframework.data.repository.CrudRepository;

import app.helice.myauth.model.Log;

/**
 *
 * @author lccf
 */
public interface LogRepository extends CrudRepository<Log, Long> {
    
}
