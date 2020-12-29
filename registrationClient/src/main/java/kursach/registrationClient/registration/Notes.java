//        User user = new User();
//       Auth.login(user);
//      List<UserLogin> userA;
//        userA = ClientBuilder.newBuilder()
//                .register(JacksonJsonProvider.class)
//                .build()
//                .target("http://localhost")
//                .path("Auth/sendLogins")
//                .request("application/json")
//                .header("Content-Type","application/json")
//                .get(List.class);