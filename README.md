
postgresql json demo

see https://github.com/vladmihalcea/hibernate-types

JPA에서 RDBMS의 jsonb 컬럼을 공식적으로 지원하지 않아서 native 쿼리를 사용해야만 한다. 

```java
    @Query(value = "select * from book where properties ->> 'publisher' = :publisher",nativeQuery = true)
    List<Book> findByPublisher(@Param("publisher") String publisher);
```