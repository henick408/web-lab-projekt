import Link from "next/link";
import Pattern from "../assets/pattern";
export default function Header() {
  return (
    <div className=" flex h-28 w-full items-center justify-between bg-blue-300 opacity-90">
      <Pattern className="fill-blue-300 bg-zinc-50 h-28 " />
      <div className=" flex items-center  gap-4 ">
        <div
          style={{ fontFamily: "EB Garamond, serif" }} //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
          className=" text-5xl text-white select-none "
        >
          FORUM ROMANUM
        </div>
      </div>
      <Pattern className="fill-blue-300 bg-zinc-50 h-28" />
    </div>
  );
}
