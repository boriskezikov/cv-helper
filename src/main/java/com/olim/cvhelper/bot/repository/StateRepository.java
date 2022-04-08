package com.olim.cvhelper.bot.repository;

import com.olim.cvhelper.bot.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
