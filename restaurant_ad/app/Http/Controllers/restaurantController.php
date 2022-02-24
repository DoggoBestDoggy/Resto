<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class restaurantController extends Controller
{
    function index(){
        return Restaurant::all();
    }
}
