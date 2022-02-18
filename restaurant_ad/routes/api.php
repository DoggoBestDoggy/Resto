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

Route::post('/register'), function($login, $password, $email, $name, $firstname, $age){
    return 'users'.$login, 'user'.$password, 'user'.$email, 'user'.$name, 'user'.$firstname, 'user'.$age;
};

Route::post('/auth'), function($login, $password){
    return 'users'.$login, 'users'.$password;
};

Route::get('/users'), function(){
    return 'user';
};

Route::get('/restaurants'), function(){
    return 'restaurant';
};

Route::post('/restaurant'), function($name, $description, $grade, $localization, $phone_number, $website, $hours){
    return 'restaurant'.n$ame, 'restaurant'.$description, 'restaurant'.$grade, 'restaurant'.$localization, 'restaurant'.$phone_number, 'restaurant'.$website, 'restaurant'.$hours;
};

Route::put('/restaurant/{id}'), function($name, $description, $grade, $localization, $phone_number, $website,  $hours){
    return 'restaurant'.$name, 'restaurant'.$description, 'restaurant'.$grade, 'restaurant'.$localization, 'restaurant'.$phone_number, 'restaurant'.$website;
};

Route::delete('restaurant/{id}'), function(){
    return 'restaurant';
};

Route::get('/restaurant/{id}/menus'), function(){
    return 'restaurant';
};

Route::post('/restaurant/{id}/menu/{id}'), function($name, $description, $price){
    return 'restaurant'.$name, 'restaurant'.$description, 'restaurant'.$price;
};

Route::put('/restaurant/{id}/menu{id}'), function($name, $description, $price){
    return 'menu'.$name, 'menu'.$description, 'menu'.$price;
};

Route::delete('/restaurant/{id}/menu/{id}'), function(){
    return 'restaurant';
};
