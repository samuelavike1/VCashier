<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasOne;

class TransactionItem extends Model
{
    use HasFactory;

    protected $fillable = [
        'transaction_id',
        'product_variation_id',
        'quantity',
        'price',
        'subtotal',
        'profit',
    ];

    public function transaction(): BelongsTo
    {
        return $this->belongsTo(Transaction::class);
    }

    protected function price(): Attribute
    {
        return Attribute::make(
            get: fn(string $value) => "Rp" . number_format($value, 0, ',', '.'),
            set: fn(string $value) => (int)preg_replace("/[^0-9]/", "", $value)
        );
    }

    public function productVariation(): HasOne
    {
        return $this->hasOne(ProductVariation::class, 'id', 'product_variation_id');
    }
}
