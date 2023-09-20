// package com.aurora.sptest.controller;
//
// import com.aurora.dtblock.RedisLock;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// /**
//  * @author panda00hi
//  * @date 2023.08.29
//  */
// @RestController
// @RequestMapping("/dtbLock")
// public class DtbLockController {
//     private final RedisLock redisLock;
//
//     public DtbLockController(RedisLock redisLock) {
//         this.redisLock = redisLock;
//     }
//
//
//     @GetMapping("/lock")
//     public String lock() {
//         boolean locked = redisLock.tryLock("myLock");
//         if (locked) {
//             try {
//                 // 执行需要加锁的业务逻辑
//                 Thread.sleep(5000);
//                 return "Lock acquired successfully.";
//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//             } finally {
//                 redisLock.unlock("myLock");
//             }
//         }
//         return "Failed to acquire lock.";
//     }
// }
