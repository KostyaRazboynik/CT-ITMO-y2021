cmake_minimum_required(VERSION 3.21)
project(Lab_2 C)

set(CMAKE_C_STANDARD 99)
find_package(ZLIB REQUIRED)

add_executable(Lab_2 lab2.c)

target_link_libraries(Lab_2 ZLIB::ZLIB)

target_link_libraries(Lab_2 ${CMAKE_SOURCE_DIR}/cmake-build-debug/libdeflate.dll)
target_link_libraries(Lab_2 ${CMAKE_SOURCE_DIR}/cmake-build-debug/isa-l.dll)


