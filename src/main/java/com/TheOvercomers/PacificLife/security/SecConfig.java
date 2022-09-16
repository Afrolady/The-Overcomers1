package com.TheOvercomers.PacificLife.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource; //copia virtual de la base de datos donde consulta la informacion de la base de datos de una forma mas eficiente.

    /*@Autowired
    CustomSuccessHandler customSuccessHandler;*/

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()) //Va a la base de datos, desencripta la contraseña y despues compara ese valor con la contraseña que esta ingresando el usuario para log in.
                .dataSource(dataSource) //esto es la continuacion de la linea de arriba pero como es muy larga se inicia aqui con punto
                .usersByUsernameQuery("select correo,password,estado from empleado where correo=?") //Con este se puede sacar el nombre de usuario y la contraseña de aqui de esta Query /kwery/ Consulta .  Query es una consulta por una palabra clave o especifica
                .authoritiesByUsernameQuery("select correo, rol from empleado where correo=?"); //Aqui verifica cual es mi rol en la empresa y que estoy autorizado a hacer con ese rol. (operativo tiene acceso restringido mientras que administrativo puede acceder a toda la informacion). De esta forma tanto los empleados como los jefes pueden determinar ciertas tareas
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","VerEmpresas/**").hasRole("ADMIN")
                .antMatchers("/VerEmpleados/**").hasRole("ADMIN")
                .antMatchers("/Empresa/**").hasRole("ADMIN")
                .antMatchers("/Empleado/**").hasRole("ADMIN")
                .antMatchers("/VerMovimiento/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/AgregarMovimiento/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/EditarMovimiento/**").hasAnyRole("ADMIN","USER")
               // .and().formLogin().successHandler(customSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/Denegado")
                .and().logout().permitAll();
    }

}
