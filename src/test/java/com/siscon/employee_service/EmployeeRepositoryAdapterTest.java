package com.siscon.employee_service;

import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeJpaRepository;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EmployeeRepositoryAdapterTest {

    private EmployeeJpaRepository jpaRepository;
    private EmployeeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(EmployeeJpaRepository.class);
        adapter = new EmployeeRepositoryAdapter(jpaRepository);
    }

    @Test
    void shouldCallFindAll() {
        List<Employee> mockList = Arrays.asList(new Employee(), new Employee());
        when(jpaRepository.findAll()).thenReturn(mockList);

        List<Employee> result = adapter.findAll();

        assertThat(result).hasSize(2);
        verify(jpaRepository).findAll();
    }

    @Test
    void shouldCallFindById() {
        Long id = 1L;
        Employee emp = new Employee();
        when(jpaRepository.findById(id)).thenReturn(Optional.of(emp));

        Optional<Employee> result = adapter.findById(id);

        assertThat(result).contains(emp);
        verify(jpaRepository).findById(id);
    }

    @Test
    void shouldCallDeleteById() {
        Long id = 1L;

        adapter.deleteById(id);

        verify(jpaRepository).deleteById(id);
    }

    @Test
    void shouldCallSaveAll() {
        List<Employee> list = Arrays.asList(new Employee(), new Employee());
        when(jpaRepository.saveAll(list)).thenReturn(list);

        List<Employee> result = adapter.saveAll(list);

        assertThat(result).isEqualTo(list);
        verify(jpaRepository).saveAll(list);
    }

    @Test
    void shouldCallSave() {
        Employee emp = new Employee();
        when(jpaRepository.save(emp)).thenReturn(emp);

        Employee result = adapter.save(emp);

        assertThat(result).isEqualTo(emp);
        verify(jpaRepository).save(emp);
    }
}
