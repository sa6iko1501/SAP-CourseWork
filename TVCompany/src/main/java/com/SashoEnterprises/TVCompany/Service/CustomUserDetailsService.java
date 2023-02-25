package com.SashoEnterprises.TVCompany.Service;

import com.SashoEnterprises.TVCompany.Models.*;
import com.SashoEnterprises.TVCompany.Repositories.ContractRepository;
import com.SashoEnterprises.TVCompany.Repositories.UserRepository;
import com.SashoEnterprises.TVCompany.Requests.EditUserRequest;
import com.SashoEnterprises.TVCompany.Requests.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Getter
@Setter
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final BCryptPasswordEncoder encoder;
    @Autowired
    private final ContractService contractService;
    @Autowired
    private ContractRepository contractRepository;

    public CustomUserDetailsService() {
        this.userRepo = new UserRepository() {
            @Override
            public User findUserById(int id) {
                return null;
            }

            @Override
            public User findUserByUsername(String username) {
                return null;
            }

            @Override
            public User findUserByMail(String mail) {
                return null;
            }

            @Override
            public User findUserByPhone(String phone) {
                return null;
            }

            @Override
            public List<User> findAll() {
                return null;
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<User> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(Integer integer) {
                return null;
            }

            @Override
            public User getById(Integer integer) {
                return null;
            }

            @Override
            public User getReferenceById(Integer integer) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public Optional<User> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        this.encoder = new BCryptPasswordEncoder();
        this.contractService = new ContractService(new ContractRepository() {
            @Override
            public Contract findContractById(int id) {
                return null;
            }

            @Override
            public List<Contract> findContractsByChannelsContains(Channel channel) {
                return null;
            }

            @Override
            public List<Contract> findAll() {
                return null;
            }

            @Override
            public List<Contract> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Contract> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public <S extends Contract> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Contract> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Contract> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Contract> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Contract getOne(Integer integer) {
                return null;
            }

            @Override
            public Contract getById(Integer integer) {
                return null;
            }

            @Override
            public Contract getReferenceById(Integer integer) {
                return null;
            }

            @Override
            public <S extends Contract> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Contract> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Contract> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Contract> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Contract> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Contract entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends Contract> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Contract> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Contract> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Contract> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Contract> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Contract, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        });
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepo.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Incorrect username");
        }
        else {
            UserDetails userDetails = userRepo.findUserByUsername(user.getUsername());
            return new org.springframework.security.core.userdetails.User(user.getMail(),user.getPassword(),user.getAuthorities());
        }
    }
    public User loadUserByMail(String mail) throws UsernameNotFoundException {
        final User user = userRepo.findUserByMail(mail);
        if(user == null){
            throw new UsernameNotFoundException("Incorrect username");
        }
        else {
            return  user;
        }
    }

    public UserDetails loadUserById(int id){
        return userRepo.findUserById(id);
    }
    public User registerUser(RegisterRequest request) throws Exception{
        if(userRepo.findUserByUsername(request.getUsername())!=null){
            throw new Exception("An account with the same username already exists");
        }
        if(userRepo.findUserByMail(request.getMail())!=null){
            throw new Exception("An account with the same e-mail already exists");
        }
        if(userRepo.findUserByPhone(request.getPhone())!=null){
            throw new Exception("An account with the same phone number already exists");
        }
        User user = new User(request.getUsername(),request.getFirst_name(),request.getFamily_name(), request.getPhone(), encoder.encode(request.getPassword()),request.getMail());
        user.addRole(new Role(1,"USER"));
        userRepo.save(user);
        return user;
    }
    public List<User> loadAllUsers(){return userRepo.findAll();}
    public void deleteUser(int id){
        User user = userRepo.findUserById(id);
        Set<Contract> contracts = user.getContracts();
        user.setContracts(null);
        userRepo.save(user);
        if(contracts!=null){
            for(Contract contract: contracts){
                contract.removeUser();
                contract.removeAllChannels();
                contractRepository.save(contract);
                contractService.deleteContract(contract.getId());
            }
        }
        userRepo.delete(user);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void editUser(int id,EditUserRequest request) throws Exception{
        User user = userRepo.findUserById(id);
        if(!request.getUsername().isEmpty()){
            if(userRepo.findUserByUsername(request.getUsername())!=null&& !request.getUsername().equals(user.getUsername())){
                throw new Exception("username is already taken!");
            }
            else{
                user.setUsername(request.getUsername());
            }
        }
        if(!request.getMail().isEmpty()){
            if(userRepo.findUserByMail(request.getMail())!=null&&!request.getMail().equals(user.getMail())){
                throw  new Exception("This e_mail is already taken");
            }
            else{
                user.setMail(request.getMail());
            }
        }
        if(!request.getPhone().isEmpty()){
            if(userRepo.findUserByPhone(request.getPhone())!=null&&!request.getPhone().equals(user.getPhone())){
                throw new Exception("This phone number is already in use");
            }
            else{
                user.setPhone(request.getPhone());
            }
        }
        if(!request.getPassword().isEmpty()){
            user.setPassword(encoder.encode(request.getPassword()));
        }
        userRepo.save(user);
    }
}
