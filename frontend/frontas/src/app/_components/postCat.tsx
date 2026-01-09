import MDEditor, { commands } from "@uiw/react-md-editor";
import axios from "axios";
import React, { useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";

type CatType = {
  name: string;
};

export default function PostCatForm() {
  const [serverError, setServerError] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    reset,
    formState: { isSubmitting, errors },
  } = useForm<CatType>({
    mode: "onChange",
    defaultValues: {
      name: "",
    },
  });

  const onSubmit: SubmitHandler<CatType> = async (data) => {
    setServerError(null);

    try {
      const payload = { ...data };
      await postCat(payload);
      reset();
      window.location.reload();
    } catch (error) {
      if (axios.isAxiosError(error)) {
        if (error.response?.status === 409) {
          setServerError("Taka kategoria już istnieje");
        } else {
          setServerError("Wystąpił błąd serwera");
        }
      }
    }
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      style={{ fontFamily: "EB Garamond, serif" }}
      className="w-11/12 border border-blue-200 text-zinc-800 p-4 rounded mt-2 flex flex-col gap-4 items-center "
    >
      {/* name */}
      <div>
        <label className="">Nazwa kategorii:</label>
        <input
          className="border-blue-200 border rounded-xl p-1.5 w-full"
          {...register("name", {
            required: "Pole jest wymagane",
            minLength: { value: 1, message: "Za krótka nazwa" },
            maxLength: { value: 32, message: "Za długa nazwa" },
          })}
        />
        {errors.name && (
          <p className="text-red-600 text-sm mt-1">{errors.name.message}</p>
        )}
      </div>
      {/* błąd */}
      {serverError && (
        <p className="text-red-600 text-sm mt-1">{serverError}</p>
      )}
      {/* submit */}
      <button
        type="submit"
        disabled={isSubmitting}
        className="bg-blue-200 text-zinc-800 px-4 py-2 rounded-2xl hover:bg-blue-300 hover:cursor-pointer text-lg"
      >
        {isSubmitting ? "Wysyłanie..." : "Dodaj kategorię"}
      </button>
    </form>
  );
}

async function postCat(data: CatType) {
  const response = await axios.post(
    "http://localhost:8080/api/categories",
    data,
    {
      headers: { "Content-Type": "application/json" },
    }
  );
  return response.data;
}
