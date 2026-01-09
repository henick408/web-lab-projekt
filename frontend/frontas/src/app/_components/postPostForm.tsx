import MDEditor, { commands } from "@uiw/react-md-editor";
import axios from "axios";
import React, { useState, useEffect } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import Select from "react-select";
import PostCatForm from "./postCat";

type CategoryType = {
  id: number;
  name: string;
};

type PostType = {
  username: string;
  title: string;
  content: string;
  categoryId: number;
};

export default function PostPostForm() {
  const [mdValue, setMdValue] = useState<string>("");
  const [categories, setCategories] = useState<CategoryType[]>([]);
  const [showAdd, setShowAdd] = useState(false);

  const {
    register,
    handleSubmit,
    reset,
    setValue,
    formState: { isSubmitting, isSubmitSuccessful, errors },
  } = useForm<PostType>({
    mode: "onChange",
    defaultValues: {
      username: "",
      title: "",
      content: "",
      categoryId: 0,
    },
  });

  useEffect(() => {
    async function fetchCategories() {
      try {
        const response = await axios.get<CategoryType[]>(
          "http://localhost:8080/api/categories"
        );
        setCategories(response.data);
      } catch (err) {
        console.error("Błąd pobierania kategorii:", err);
      }
    }
    fetchCategories();
  }, []);

  const onSubmit: SubmitHandler<PostType> = async (data) => {
    if (!mdValue.trim()) {
      alert("Treść wpisu nie może być pusta");
      return;
    }
    const payload = { ...data, content: mdValue };
    console.log("POST PAYLOAD:", payload);
    await postPost(payload);
    reset();
    setMdValue("");
    window.location.reload();
  };

  const sortedCategories = [...categories].sort((a, b) =>
    a.name.localeCompare(b.name)
  );

  const categoryOptions = sortedCategories.map((c) => ({
    value: c.id,
    label: c.name.charAt(0).toUpperCase() + c.name.slice(1),
  }));

  return (
    <div
      className="w-11/12 border border-blue-200 text-zinc-800 p-4 rounded mt-2 "
      style={{ fontFamily: "EB Garamond, serif" }}
    >
      <div className="text-xl">{showAdd && "Dodaj własną kategorię:"}</div>

      {showAdd && <PostCatForm />}
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4  ">
        {/* username */}
        <div>
          <label>Nazwa użytkownika:</label>
          <input
            className="border-blue-200 border rounded-xl p-1.5 w-full"
            {...register("username", {
              required: "Pole jest wymagane",
              minLength: { value: 1, message: "Za krótka nazwa" },
              maxLength: { value: 32, message: "Za długa nazwa" },
            })}
          />
          {errors.username && (
            <p className="text-red-600 text-sm mt-1">
              {errors.username.message}
            </p>
          )}
        </div>

        {/* title */}
        <div>
          <label className="">Tytuł:</label>
          <input
            className="border-blue-200 border rounded-xl  p-1.5 w-full"
            {...register("title", {
              required: "Pole jest wymagane",
              minLength: { value: 1, message: "Za krótki tutuł" },
              maxLength: { value: 255, message: "Za długi tutył" },
            })}
          />
          {errors.title && (
            <p className="text-red-600 text-sm mt-1">{errors.title.message}</p>
          )}
        </div>

        {/* category */}
        <div>
          <label className="">Kategoria:</label>
          <Select
            required={true}
            options={categoryOptions}
            placeholder=""
            onChange={(option) => setValue("categoryId", option?.value || 0)}
            isClearable
            styles={{
              control: (provided, state) => ({
                ...provided,
                borderRadius: "0.75rem",
                borderWidth: state.isFocused ? "2px " : "1px",
                borderColor: state.isFocused ? "#000000" : "#93c5fd",
                boxShadow: "none",
                cursor: "text",
                "&:hover": {
                  borderColor: state.isFocused ? "#000000" : "#93c5fd",
                },
                padding: "0.1rem 0.5rem",
                fontFamily: "EB Garamond, serif",
              }),
              placeholder: (provided) => ({
                ...provided,
                color: "#1e293b",
              }),
              singleValue: (provided) => ({
                ...provided,
                color: "#1e293b",
              }),
              menu: (provided) => ({
                ...provided,
                borderRadius: "0.75rem",
                fontFamily: "EB Garamond, serif",
              }),
              option: (provided, state) => ({
                ...provided,
                backgroundColor: state.isFocused ? "#bfdbfe" : "white",
                color: "#1e293b",
                cursor: "pointer",
                borderColor: "#93c5fd",
              }),
            }}
          />
          <div
            style={{ fontFamily: "EB Garamond, serif" }}
            className="p-2 px-4 bg-blue-100 text-black hover:bg-blue-200 hover:cursor-pointer text-base w-fit rounded-lg text-center mt-2"
            onClick={() => {
              setShowAdd(!showAdd);
            }}
          >
            {showAdd
              ? "Anuluj dodawanie kategorii"
              : "Nie ma kategorii, której szukasz? dodaj sam!"}
          </div>

          {errors.categoryId && (
            <p className="text-red-600 text-sm mt-1">
              {errors.categoryId.message}
            </p>
          )}
        </div>

        {/* md editor */}
        <div data-color-mode="light">
          <label className="font-semibold"></label>
          <div className="border-blue-300 border rounded ">
            <MDEditor
              value={mdValue}
              onChange={(val) => setMdValue(val || "")}
              visibleDragbar={false}
              preview="edit"
              height={340}
              commands={[
                commands.bold,
                commands.italic,
                commands.strikethrough,
                commands.hr,
                commands.image,
              ]}
              extraCommands={[commands.codeEdit, commands.codePreview]}
              textareaProps={{
                style: { fontFamily: "EB Garamond, serif" },
              }}
            />
          </div>
        </div>

        {/* submit */}
        <button
          type="submit"
          disabled={isSubmitting}
          className="bg-blue-200 mx-6 text-zinc-800 px-4 py-2 rounded-2xl hover:bg-blue-300 hover:cursor-pointer text-lg"
        >
          {isSubmitting ? "Wysyłanie..." : "Dodaj wpis"}
        </button>
      </form>
    </div>
  );
}

async function postPost(data: PostType) {
  const response = await axios.post("http://localhost:8080/api/posts", data, {
    headers: { "Content-Type": "application/json" },
  });
  return response.data;
}
