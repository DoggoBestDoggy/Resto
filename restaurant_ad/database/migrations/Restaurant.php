<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('rest_ad_restaurant', function (Blueprint $table) {
            $table->id();
            $table->name(string);
            $table->description(string);
            $table->grade(float);
            $table->localisation(string);
            $table->phone_number(string);
            $table->website(string);
            $table->hours(string);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('rest_ad_restaurant');
    }
};
