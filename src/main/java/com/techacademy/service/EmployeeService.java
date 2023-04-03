package com.techacademy.service;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


        public EmployeeService(EmployeeRepository repository) {
            this.employeeRepository = repository;
        }

        /** 全件を検索して返す */
        public List<Employee> getEmployeeList() {
            // リポジトリのfindAllメソッドを呼び出す
            return employeeRepository.findAll();
        }

        /** 従業員を1件検索して返す */
        public Employee getEmployee(Integer id) {
            return employeeRepository.findById(id).get();
        }

        /** 従業員の登録を行う */
        @Transactional
        public Employee saveEmployee(Employee employee) {
            employee.setDeleteFlag(0);
            employee.setCreatedAt(new Date(new java.util.Date().getTime()));
            employee.setUpdatedAt(new Date(new java.util.Date().getTime()));



            Authentication authentication = employee.getAuthentication();

            authentication.setEmployee(employee);

            return employeeRepository.save(employee);
        }

        /** 従業員の更新を行う */
        @Transactional
        public Employee updateEmployee(Employee employee) {

            Employee currentEmployee = getEmployee(employee.getId());
            employee.setCreatedAt(currentEmployee.getCreatedAt());
            employee.setUpdatedAt(new Date(new java.util.Date().getTime()));

            Authentication authentication = employee.getAuthentication();

            authentication.setEmployee(employee);

            return employeeRepository.save(employee);
        }



        // ----- 追加:ここから -----
        /** 従業員の削除を行う */
        @Transactional
        public Employee deleteEmployee(Employee employee) {
            employee.setDeleteFlag(1);
            Employee currentEmployee = getEmployee(employee.getId());
            employee.setCreatedAt(currentEmployee.getCreatedAt());
            employee.setUpdatedAt(new Date(new java.util.Date().getTime()));
            Authentication authentication = employee.getAuthentication();

            authentication.setEmployee(employee);

            return employeeRepository.save(employee);
        }
        }


