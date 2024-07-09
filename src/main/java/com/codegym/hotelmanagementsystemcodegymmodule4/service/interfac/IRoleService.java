package com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;

public interface IRoleService {
    Role findByName(String name);
}
