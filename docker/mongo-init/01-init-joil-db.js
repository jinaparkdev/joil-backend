
db.createUser({
  user: 'joiluser',
  pwd: 'joilpassword',
  roles: [
    {
      role: 'readWrite',
      db: 'joil'
    }
  ]
});

print('joil 데이터베이스와 사용자가 생성되었습니다.');
