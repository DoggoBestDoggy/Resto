<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/register'), function(login, password, email, name, firstname, age){

};

Route::post('/auth'), function(login, password){

};

Route::get('/users'), function(){

};

Route::get('/restaurants'), function(){

};

Route::post('/restaurant'), function(name, description, grade, localization, phone_number, website, hours){

};

Route::put('/restaurant/{id}'), function(name, description, grade, localization, phone_number, website, hours){

};

Route::delete('restaurant/{id}'), function(){

};

Route::get('/restaurant/{id}/menus'), function(){

};

Route::post('/restaurant/{id}/menu/{id}'), function(name, description, price){

};

Route::put('/restaurant/{id}/menu{id}'), function(name, description, price){

};

Route::delete('/restaurant/{id}/menu/{id}'), function(){

};
